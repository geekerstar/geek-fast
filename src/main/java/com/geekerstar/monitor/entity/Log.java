package com.geekerstar.monitor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.geekerstar.common.converter.TimeConverter;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@Excel("系统日志表")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "日志id")
    private Long id;

    @ApiModelProperty(value = "操作用户")
    @TableField("username")
    @ExcelField(value = "操作用户")
    private String username;

    @ApiModelProperty(value = "操作内容")
    @TableField("operation")
    @ExcelField(value = "操作内容")
    private String operation;

    @ApiModelProperty(value = "耗时")
    @TableField("time")
    @ExcelField(value = "耗时（毫秒）")
    private Long time;

    @ApiModelProperty(value = "操作方法")
    @TableField("method")
    @ExcelField(value = "操作方法")
    private String method;

    @ApiModelProperty(value = "方法参数")
    @TableField("params")
    @ExcelField(value = "方法参数")
    private String params;

    @ApiModelProperty(value = "操作者ip")
    @TableField("ip")
    @ExcelField(value = "操作者IP")
    private String ip;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @ExcelField(value = "操作时间", writeConverter = TimeConverter.class)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "操作地点")
    @TableField("location")
    @ExcelField(value = "操作地点")
    private String location;

    private transient String createTimeFrom;
    private transient String createTimeTo;


}
