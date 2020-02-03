package com.geekerstar.monitor.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.net.URI;

/**
 * @author geekerstar
 * @date 2020/2/1 11:59
 * @description
 */
@Data
@ApiModel("请求追踪实体")
public class GeekHttpTrace implements Serializable {

    private static final long serialVersionUID = -7930808804323157107L;

    @ApiModelProperty("请求时间")
    private String requestTime;

    @ApiModelProperty("请求方法")
    private String method;

    @ApiModelProperty("请求 url")
    private URI url;

    @ApiModelProperty("响应状态")
    private int status;

    @ApiModelProperty("耗时")
    private Long timeTaken;
}
