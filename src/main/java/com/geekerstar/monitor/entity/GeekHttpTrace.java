package com.geekerstar.monitor.entity;

import lombok.Data;

import java.io.Serializable;
import java.net.URI;

/**
 * @author geekerstar
 * @date 2020/2/1 11:59
 * @description
 */
@Data
public class GeekHttpTrace implements Serializable {

    private static final long serialVersionUID = -7930808804323157107L;

    /**
     * 请求时间
     */
    private String requestTime;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求 url
     */
    private URI url;
    /**
     * 响应状态
     */
    private int status;
    /**
     * 耗时
     */
    private Long timeTaken;
}
