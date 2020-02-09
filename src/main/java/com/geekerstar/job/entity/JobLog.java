package com.geekerstar.job.entity;

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
 * <p>
 * 调度日志表
 * </p>
 *
 * @author Geekerstar
 * @since 2020-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_job_log")
@ApiModel(value = "JobLog对象", description = "调度日志表")
@Excel("调度日志信息表")
public class JobLog implements Serializable {

    private static final long serialVersionUID = 1L;

    // 任务执行成功
    public static final String JOB_SUCCESS = "0";
    // 任务执行失败
    public static final String JOB_FAIL = "1";

    @ApiModelProperty(value = "任务日志id")
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    @ApiModelProperty(value = "任务id")
    @TableField("job_id")
    private Long jobId;

    @ApiModelProperty(value = "spring bean名称")
    @TableField("bean_name")
    @ExcelField(value = "Bean名称")
    private String beanName;

    @ApiModelProperty(value = "方法名")
    @TableField("method_name")
    @ExcelField(value = "方法名称")
    private String methodName;

    @ApiModelProperty(value = "参数")
    @TableField("params")
    @ExcelField(value = "方法参数")
    private String params;

    @ApiModelProperty(value = "任务状态    0：成功    1：失败")
    @TableField("status")
    @ExcelField(value = "状态", writeConverterExp = "0=成功,1=失败")
    private String status;

    @ApiModelProperty(value = "失败信息")
    @TableField("error")
    @ExcelField(value = "异常信息")
    private String error;

    @ApiModelProperty(value = "耗时(单位：毫秒)")
    @TableField("times")
    @ExcelField(value = "耗时（毫秒）")
    private Long times;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @ExcelField(value = "执行时间", writeConverter = TimeConverter.class)
    private LocalDateTime createTime;

    private transient String createTimeFrom;
    private transient String createTimeTo;

}
