package com.geekerstar.common.entity;

import com.geekerstar.system.entity.Dept;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2020/1/31 15:59
 * @description
 */
@Data
@ApiModel(value = "部门树实体")
public class DeptTree<T> implements Serializable {

    private static final long serialVersionUID = -4061088146938175240L;

    @ApiModelProperty("部门id")
    private String id;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("链接地址")
    private String href;

    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("状态")
    private Map<String, Object> state;

    @ApiModelProperty("是否选中")
    private boolean checked = false;

    @ApiModelProperty("属性")
    private Map<String, Object> attributes;

    @ApiModelProperty("下级部门")
    private List<DeptTree<T>> children;

    @ApiModelProperty("上级部门")
    private String parentId;

    @ApiModelProperty("是否有父级")
    private boolean hasParent = false;

    @ApiModelProperty("是否有子级")
    private boolean hasChild = false;

    @ApiModelProperty("部门信息")
    private Dept data;

    public void initChildren() {
        this.children = new ArrayList<>();
    }

}
