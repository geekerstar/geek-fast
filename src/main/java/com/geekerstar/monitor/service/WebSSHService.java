package com.geekerstar.monitor.service;

import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * @author geekerstar
 * @date 2020/3/17 16:14
 * @description
 */
public interface WebSSHService {

    /**
     * 初始化ssh连接
     *
     * @param webSocketSession
     */
    void initConnection(WebSocketSession webSocketSession);


    /**
     * 处理客户段发的数据
     *
     * @param buffer
     * @param webSocketSession
     */
    void receiveHandler(String buffer, WebSocketSession webSocketSession);


    /**
     * 数据写回前端 for websocket
     *
     * @param webSocketSession
     * @param buffer
     */
    void sendMessage(WebSocketSession webSocketSession, byte[] buffer) throws IOException;

    /**
     * 关闭连接
     *
     * @param webSocketSession
     */
    void closeConnection(WebSocketSession webSocketSession);
}
