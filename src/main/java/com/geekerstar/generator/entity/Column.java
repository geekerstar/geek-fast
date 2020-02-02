package com.geekerstar.generator.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author geekerstar
 * @date 2020/2/2 13:03
 * @description
 */
@Data
@ApiModel("字段")
public class Column {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("是否为主键")
    private Boolean isKey;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("注释")
    private String remark;

    @ApiModelProperty("属性名称")
    private String field;
}
