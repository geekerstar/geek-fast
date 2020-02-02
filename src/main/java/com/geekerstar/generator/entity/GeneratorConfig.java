package com.geekerstar.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.geekerstar.common.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 代码生成配置表
 * </p>
 *
 * @author Geekerstar
 * @since 2020-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_generator_config")
@ApiModel(value = "GeneratorConfig对象", description = "代码生成配置表")
public class GeneratorConfig implements Serializable {


    public static final String TRIM_YES = "1";
    public static final String TRIM_NO = "0";

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "基础包名")
    private String basePackage;

    @ApiModelProperty(value = "entity文件存放路径")
    private String entityPackage;

    @ApiModelProperty(value = "mapper文件存放路径")
    private String mapperPackage;

    @ApiModelProperty(value = "mapper xml文件存放路径")
    private String mapperXmlPackage;

    @ApiModelProperty(value = "servcie文件存放路径")
    private String servicePackage;

    @ApiModelProperty(value = "serviceimpl文件存放路径")
    private String serviceImplPackage;

    @ApiModelProperty(value = "controller文件存放路径")
    private String controllerPackage;

    @ApiModelProperty(value = "是否去除前缀 1是 0否")
    private String isTrim;

    @ApiModelProperty(value = "前缀内容")
    private String trimValue;

    /**
     * java文件路径，固定值
     */
    private transient String javaPath = "/src/main/java/";
    /**
     * 配置文件存放路径，固定值
     */
    private transient String resourcesPath = "src/main/resources";
    /**
     * 文件生成日期
     */
    private transient String date = DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULL_TIME_SPLIT_PATTERN);

    /**
     * 表名
     */
    private transient String tableName;
    /**
     * 表注释
     */
    private transient String tableComment;
    /**
     * 数据表对应的类名
     */
    private transient String className;


}
