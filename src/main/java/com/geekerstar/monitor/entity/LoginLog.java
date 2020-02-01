package com.geekerstar.monitor.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 登录日志表
 * </p>
 *
 * @author Geekerstar
 * @since 2020-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_login_log")
@ApiModel(value = "LoginLog对象", description = "登录日志表")
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "登录时间")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "登录地点")
    private String location;

    @ApiModelProperty(value = "ip地址")
    private String ip;

    @ApiModelProperty(value = "操作系统")
    private String system;

    @ApiModelProperty(value = "浏览器")
    private String browser;


}
