package com.geekerstar.common.entity;

import com.geekerstar.system.entity.Menu;
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
public class MenuTree<T> implements Serializable {

    private static final long serialVersionUID = 8258973982006499982L;

    private String id;
    private String icon;
    private String href;
    private String title;
    private Map<String, Object> state;
    private boolean checked = false;
    private Map<String, Object> attributes;
    private List<MenuTree<T>> childs = new ArrayList<>();
    private String parentId;
    private boolean hasParent = false;
    private boolean hasChild = false;

    private Menu data;
}
