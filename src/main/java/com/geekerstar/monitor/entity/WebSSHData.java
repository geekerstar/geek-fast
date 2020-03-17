package com.geekerstar.monitor.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author geekerstar
 * @date 2020/3/17 16:26
 * @description
 */
@Getter
@Setter
@ApiModel("webssh传输数据")
public class WebSSHData {
    /**
     * 操作
     */
    private String operate;

    private String host;

    /**
     * 端口号默认为22
     */
    private Integer port = 22;

    private String username;

    private String password;

    private String command = "";
}
