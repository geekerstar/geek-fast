package com.geekerstar.monitor.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author geekerstar
 * @date 2020/2/1 12:38
 * @description
 */
@Data
@ApiModel("Tomcat 信息")
public class TomcatInfo implements Serializable {

    private static final long serialVersionUID = 5288277075399619286L;

    @ApiModelProperty("tomcat 已创建 session 数")
    private Double tomcatSessionsCreated;

    @ApiModelProperty("tomcat 已过期 session 数")
    private Double tomcatSessionsExpired;

    @ApiModelProperty("tomcat 当前活跃 session 数")
    private Double tomcatSessionsActiveCurrent;

    @ApiModelProperty("tomcat 活跃 session 数峰值")
    private Double tomcatSessionsActiveMax;

    @ApiModelProperty("超过session 最大配置后，拒绝的 session 个数")
    private Double tomcatSessionsRejected;

    @ApiModelProperty("发送的字节数")
    private Double tomcatGlobalSent;

    @ApiModelProperty("request 请求最长耗时")
    private Double tomcatGlobalRequestMax;

    @ApiModelProperty("tomcat 全局异常数量")
    private Double tomcatGlobalError;

    @ApiModelProperty("tomcat 当前线程数（包括守护线程）")
    private Double tomcatThreadsCurrent;

    @ApiModelProperty("tomcat 配置的线程最大数")
    private Double tomcatThreadsConfigMax;

    @ApiModelProperty("tomcat 当前繁忙线程数")
    private Double tomcatThreadsBusy;
}
