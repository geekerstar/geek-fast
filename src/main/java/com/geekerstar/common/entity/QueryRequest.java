package com.geekerstar.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author geekerstar
 * @date 2020/1/31 16:53
 * @description
 */
@Data
public class QueryRequest implements Serializable {

    private static final long serialVersionUID = 3616394274624418989L;

    // 当前页面数据量
    private int pageSize = 10;
    // 当前页码
    private int pageNum = 1;
    // 排序字段
    private String field;
    // 排序规则，asc升序，desc降序
    private String order;
}
