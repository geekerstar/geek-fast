package com.geekerstar.common.exception;

/**
 * @author geekerstar
 * date: 2019/12/15 20:35
 * description:
 */
public class GeekException extends RuntimeException {

    private static final long serialVersionUID = 1483552349749791672L;

    public GeekException(String message) {
        super(message);
    }
}
