package com.geekerstar.common.entity;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * @author geekerstar
 * @date 2020/1/31 16:23
 * @description
 */
public class GeekResponse extends HashMap<String, Object> {
    private static final long serialVersionUID = -8713837118340960775L;

    public GeekResponse code(HttpStatus status) {
        this.put("code", status.value());
        return this;
    }

    public GeekResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public GeekResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    public GeekResponse success() {
        this.code(HttpStatus.OK);
        return this;
    }

    public GeekResponse fail() {
        this.code(HttpStatus.INTERNAL_SERVER_ERROR);
        return this;
    }

    @Override
    public GeekResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
