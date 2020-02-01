package com.geekerstar.monitor.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author geekerstar
 * @date 2020/2/1 12:41
 * @description
 */
@Data
@ApiModel("服务器信息")
public class ServerInfo implements Serializable {

    private static final long serialVersionUID = -4461093482160886069L;

    @ApiModelProperty("应用已运行时长")
    private Double processUptime;

    @ApiModelProperty("应用 CPU占用率")
    private Double processCpuUsage;

    @ApiModelProperty("应用启动时间点")
    private String processStartTime;

    @ApiModelProperty("系统 CPU核心数")
    private Double systemCpuCount;

    @ApiModelProperty("系统 CPU 使用率")
    private Double systemCpuUsage;

    @ApiModelProperty("当前活跃 JDBC连接数")
    private Double jdbcConnectionsActive;

    @ApiModelProperty("JDBC最小连接数")
    private Double jdbcConnectionsMin;

    @ApiModelProperty("JDBC最大连接数")
    private Double jdbcConnectionsMax;
}
