package com.geekerstar.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.geekerstar.common.converter.TimeConverter;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色表Role
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value = "Role对象", description = "角色表")
@Excel("角色信息表")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    @ExcelField(value = "角色名称")
    @NotBlank(message = "{required}")
    @Size(max = 10, message = "{noMoreThan}")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    @TableField("remark")
    @ExcelField(value = "角色描述")
    @Size(max = 50, message = "{noMoreThan}")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("modify_time")
    @ExcelField(value = "修改时间", writeConverter = TimeConverter.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime modifyTime;


    /**
     * 角色对应的菜单（按钮） id
     */
    private transient String menuIds;

}
