package com.geekerstar.monitor.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 操作日志表Log
 *
 * @author Geekerstar
 * @since 2020-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_log")
@ApiModel(value = "Log对象", description = "操作日志表")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "操作用户")
    private String username;

    @ApiModelProperty(value = "操作内容")
    private String operation;

    @ApiModelProperty(value = "耗时")
    private BigDecimal time;

    @ApiModelProperty(value = "操作方法")
    private String method;

    @ApiModelProperty(value = "方法参数")
    private String params;

    @ApiModelProperty(value = "操作者ip")
    private String ip;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "操作地点")
    private String location;


}
