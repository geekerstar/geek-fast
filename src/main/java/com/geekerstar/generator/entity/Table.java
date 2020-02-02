package com.geekerstar.generator.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author geekerstar
 * @date 2020/2/2 13:10
 * @description
 */
@Data
@ApiModel("表")
public class Table {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("数据量（行）")
    private Long dataRows;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("修改时间")
    private String updateTime;
}
