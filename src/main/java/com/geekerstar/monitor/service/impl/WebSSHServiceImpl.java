package com.geekerstar.monitor.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekerstar.monitor.constant.ConstantPool;
import com.geekerstar.monitor.entity.SSHConnectInfo;
import com.geekerstar.monitor.entity.WebSSHData;
import com.geekerstar.monitor.service.WebSSHService;
import com.google.common.collect.Maps;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author geekerstar
 * @date 2020/3/17 16:17
 * @description
 */
@Slf4j
@Service
public class WebSSHServiceImpl implements WebSSHService {

    /**
     * 存放ssh连接信息的map
     */
    private static Map<String, Object> sshMap = Maps.newConcurrentMap();

    /**
     * 线程池
     */
    private ExecutorService executorService = Executors.newCachedThreadPool();


    /**
     * 初始化连接
     *
     * @param webSocketSession
     */
    @Override
    public void initConnection(WebSocketSession webSocketSession) {
        JSch jSch = new JSch();
        SSHConnectInfo sshConnectInfo = new SSHConnectInfo();
        sshConnectInfo.setWebSocketSession(webSocketSession);
        sshConnectInfo.setJSch(jSch);
        String uuid = String.valueOf(webSocketSession.getAttributes().get(ConstantPool.USER_UUID_KEY));
        //将这个ssh连接信息放入map中
        sshMap.put(uuid, sshConnectInfo);
    }

    @Override
    public void receiveHandler(String buffer, WebSocketSession webSocketSession) {
        ObjectMapper objectMapper = new ObjectMapper();
        WebSSHData webSSHData = null;
        try {
            objectMapper.readValue(buffer, WebSSHData.class);
        } catch (IOException e) {
            log.error("Json转换异常");
            log.error("异常信息:{}", e.getMessage());
            return;
        }
        String userId = String.valueOf(webSocketSession.getAttributes().get(ConstantPool.USER_UUID_KEY));
        if (ConstantPool.WEBSSH_OPERATE_CONNECT.equals(webSSHData.getOperate())) {
            //找到刚才存储的ssh连接对象
            SSHConnectInfo sshConnectInfo = (SSHConnectInfo) sshMap.get(userId);
            //启动线程异步处理
            WebSSHData finalWebSSHData = webSSHData;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        connectToSSH(sshConnectInfo, finalWebSSHData, webSocketSession);
                    } catch (JSchException | IOException e) {
                        log.error("webssh连接异常");
                        log.error("异常信息:{}", e.getMessage());
                        closeConnection(webSocketSession);
                    }
                }
            });
        }
    }

    /**
     * 使用jsch连接终端
     *
     * @param sshConnectInfo
     * @param webSSHData
     * @param webSocketSession
     */
    private void connectToSSH(SSHConnectInfo sshConnectInfo, WebSSHData webSSHData, WebSocketSession webSocketSession) throws JSchException, IOException {
        Session session = null;
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        //获取jsch的会话
        session = sshConnectInfo.getJSch().getSession(webSSHData.getUsername(), webSSHData.getHost(), webSSHData.getPort());
        session.setConfig(config);
        //设置密码
        session.setPassword(webSSHData.getPassword());
        //连接超时时间30s
        session.connect(30000);
        //开启shell通道
        Channel channel = session.openChannel("shell");
        //通道连接超时时间3s
        channel.connect(3000);
        //设置channel
        sshConnectInfo.setChannel(channel);
        //转发消息
        transToSSH(channel, "\r");
        //读取终端返回的信息流
        InputStream inputStream = channel.getInputStream();
        try {
            //循环读取
            byte[] buffer = new byte[1024];
            int i = 0;
            //如果没有数据来，线程会一直阻塞在这个地方等待数据。
            while ((i = inputStream.read(buffer)) != -1) {
                sendMessage(webSocketSession, Arrays.copyOfRange(buffer, 0, i));
            }
        } finally {
            //断开连接后关闭会话
            session.disconnect();
            channel.disconnect();
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * 将消息转发到终端
     *
     * @param channel
     * @param command
     */
    private void transToSSH(Channel channel, String command) throws IOException {
        if (channel != null) {
            OutputStream outputStream = channel.getOutputStream();
            outputStream.write(command.getBytes());
            outputStream.flush();
        }
    }

    /**
     * 数据写回前端 for websocket
     *
     * @param webSocketSession
     * @param buffer
     * @throws IOException
     */
    @Override
    public void sendMessage(WebSocketSession webSocketSession, byte[] buffer) throws IOException {
        webSocketSession.sendMessage(new TextMessage(buffer));
    }

    /**
     * 关闭连接
     *
     * @param webSocketSession
     */
    @Override
    public void closeConnection(WebSocketSession webSocketSession) {
        String userId = String.valueOf(webSocketSession.getAttributes().get(ConstantPool.USER_UUID_KEY));
        SSHConnectInfo sshConnectInfo = (SSHConnectInfo) sshMap.get(userId);
        if (sshConnectInfo != null) {
            //断开连接
            if (sshConnectInfo.getChannel() != null) {
                sshConnectInfo.getChannel().disconnect();
            }
            //map中移除
            sshMap.remove(userId);
        }
    }
}
