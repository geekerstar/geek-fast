package com.geekerstar.monitor.entity;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author geekerstar
 * @date 2020/3/17 16:20
 * @description
 */
@Getter
@Setter
@ApiModel("SSH连接信息")
public class SSHConnectInfo {

    private WebSocketSession webSocketSession;

    private JSch jSch;

    private Channel channel;
}
