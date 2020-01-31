package com.geekerstar.common.annotation;

import java.lang.annotation.*;

/**
 * @author geekerstar
 * date: 2019-12-21 10:29
 * description: AOP注解，注解用在Controller方法，拦截请求响应信息
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Weblog {
    /**
     * 日志描述信息
     *
     * @return
     */
    String description() default "";
}
