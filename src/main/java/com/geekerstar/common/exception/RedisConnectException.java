package com.geekerstar.common.exception;

/**
 * @author geekerstar
 * @date 2020/2/1 11:43
 * @description Redis连接异常
 */
public class RedisConnectException extends Exception {

    private static final long serialVersionUID = -2993549563412870971L;

    public RedisConnectException(String message) {
        super(message);
    }

}
