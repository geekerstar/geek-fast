package com.geekerstar.common.entity;

import com.geekerstar.system.entity.Dept;
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
public class DeptTree<T> implements Serializable {

    private static final long serialVersionUID = -4061088146938175240L;

    private String id;
    private String icon;
    private String href;
    private String name;
    private Map<String, Object> state;
    private boolean checked = false;
    private Map<String, Object> attributes;
    private List<DeptTree<T>> children;
    private String parentId;
    private boolean hasParent = false;
    private boolean hasChild = false;

    private Dept data;

    public void initChildren() {
        this.children = new ArrayList<>();
    }

}
