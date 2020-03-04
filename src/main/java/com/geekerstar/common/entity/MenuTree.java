package com.geekerstar.common.entity;

import com.geekerstar.system.entity.Menu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2020/1/31 16:08
 * @description
 */
@Data
@ApiModel("菜单树")
public class MenuTree<T> implements Serializable {

    private static final long serialVersionUID = 8258973982006499982L;

    @ApiModelProperty("菜单id")
    private String id;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("菜单路径")
    private String href;

    @ApiModelProperty("菜单标题")
    private String title;

    @ApiModelProperty("状态")
    private Map<String, Object> state;

    @ApiModelProperty("是否选中")
    private boolean checked = false;

    @ApiModelProperty("属性")
    private Map<String, Object> attributes;

    @ApiModelProperty("子菜单")
    private List<MenuTree<T>> childs = new ArrayList<>();

    @ApiModelProperty("父菜单")
    private String parentId;

    @ApiModelProperty("是否有父节点")
    private boolean hasParent = false;

    @ApiModelProperty("是否有子节点")
    private boolean hasChild = false;

    @ApiModelProperty("菜单数据")
    private Menu data;
}
