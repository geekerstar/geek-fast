package com.geekerstar.system.entity;

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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜单表Menu
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value = "Menu对象", description = "菜单表")
@Excel("菜单信息表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    // 菜单
    public static final String TYPE_MENU = "0";
    // 按钮
    public static final String TYPE_BUTTON = "1";

    public static final Long TOP_NODE = 0L;

    @ApiModelProperty(value = "菜单/按钮id")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    @ApiModelProperty(value = "上级菜单id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "菜单/按钮名称")
    @TableField("menu_name")
    @NotBlank(message = "{required}")
    @Size(max = 10, message = "{noMoreThan}")
    @ExcelField(value = "名称")
    private String menuName;

    @ApiModelProperty(value = "菜单url")
    @TableField("url")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "URL")
    private String url;

    @ApiModelProperty(value = "权限标识")
    @TableField("perms")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "权限")
    private String perms;

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "图标")
    private String icon;

    @ApiModelProperty(value = "类型 0菜单 1按钮")
    @TableField("type")
    @NotBlank(message = "{required}")
    @ExcelField(value = "类型", writeConverterExp = "0=按钮,1=菜单")
    private String type;

    @ApiModelProperty(value = "排序")
    @TableField("order_num")
    private Long orderNum;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("modify_time")
    @ExcelField(value = "修改时间", writeConverter = TimeConverter.class)
    private LocalDateTime modifyTime;


}
