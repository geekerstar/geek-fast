package com.geekerstar.common.exception;

/**
 * @author geekerstar
 * date: 2019/12/15 20:53
 * description: 限流异常
 */
public class LimitAccessException extends Exception {

    private static final long serialVersionUID = 1797088813078992787L;

    public LimitAccessException(String message) {
        super(message);
    }

}
