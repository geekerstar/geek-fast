package com.geekerstar.monitor.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author geekerstar
 * @date 2020/2/1 12:25
 * @description
 */
@Data
@ApiModel("在线用户")
public class ActiveUser implements Serializable {

    private static final long serialVersionUID = 2236475903322203445L;

    @ApiModelProperty("session id")
    private String id;

    @ApiModelProperty("用户 id")
    private String userId;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("用户主机地址")
    private String host;

    @ApiModelProperty("用户登录时系统 IP")
    private String systemHost;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("session 创建时间")
    private String startTimestamp;

    @ApiModelProperty("session 最后访问时间")
    private String lastAccessTime;

    @ApiModelProperty("超时时间")
    private Long timeout;

    @ApiModelProperty("所在地")
    private String location;

    @ApiModelProperty("是否为当前登录用户")
    private boolean current;
}
