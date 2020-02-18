package com.geekerstar.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.geekerstar.common.annotation.IsMobile;
import com.geekerstar.common.converter.TimeConverter;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表User
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Data
@Excel("用户信息表")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    // 用户状态：有效
    public static final String STATUS_VALID = "1";
    // 用户状态：锁定
    public static final String STATUS_LOCK = "0";
    // 默认头像
    public static final String DEFAULT_AVATAR = "default.jpg";
    // 默认密码
    public static final String DEFAULT_PASSWORD = "111111";
    // 性别男
    public static final String SEX_MALE = "0";
    // 性别女
    public static final String SEX_FEMALE = "1";
    // 性别保密
    public static final String SEX_UNKNOW = "2";
    // 黑色主题
    public static final String THEME_BLACK = "black";
    // 白色主题
    public static final String THEME_WHITE = "white";
    // TAB开启
    public static final String TAB_OPEN = "1";
    // TAB关闭
    public static final String TAB_CLOSE = "0";

    @ApiModelProperty(value = "用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    @Size(min = 1, max = 15, message = "{range}")
    @ExcelField(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "部门id")
    @TableField("dept_id")
    private Long deptId;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    @Size(max = 50, message = "{noMoreThan}")
    @Email(message = "{email}")
    @ExcelField(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "联系电话")
    @TableField("mobile")
    @IsMobile(message = "{mobile}")
    @ExcelField(value = "联系电话")
    private String mobile;

    @ApiModelProperty(value = "状态 0锁定 1有效")
    @TableField("status")
    @NotBlank(message = "{required}")
    @ExcelField(value = "状态", writeConverterExp = "0=锁定,1=有效")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("modify_time")
    @ExcelField(value = "修改时间", writeConverter = TimeConverter.class)
    private LocalDateTime modifyTime;

    @ApiModelProperty(value = "最近访问时间")
    @TableField("last_login_time")
    @ExcelField(value = "最近访问时间", writeConverter = TimeConverter.class)
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "性别 0男 1女 2保密")
    @TableField("sex")
    @NotBlank(message = "{required}")
    @ExcelField(value = "性别", writeConverterExp = "0=男,1=女,2=保密")
    private String sex;

    @ApiModelProperty(value = "是否开启tab，0关闭 1开启")
    @TableField("is_tab")
    private String isTab;

    @ApiModelProperty(value = "主题")
    @TableField("theme")
    private String theme;

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    @Size(max = 100, message = "{noMoreThan}")
    @ExcelField(value = "个人描述")
    private String description;


    @ApiModelProperty("部门名称")
    @TableField(exist = false)
    @ExcelField(value = "部门")
    private String deptName;

    @ApiModelProperty("创建时间初值")
    @TableField(exist = false)
    private String createTimeFrom;

    @ApiModelProperty("创建时间终值")
    @TableField(exist = false)
    private String createTimeTo;

    @ApiModelProperty("角色id")
    @NotBlank(message = "{required}")
    @TableField(exist = false)
    private String roleId;

    @ApiModelProperty("角色名")
    @TableField(exist = false)
    private String roleName;

    public Long getId() {
        return userId;
    }

}
