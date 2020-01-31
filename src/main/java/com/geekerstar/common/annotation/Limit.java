package com.geekerstar.common.annotation;

import com.geekerstar.common.entity.LimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author geekerstar
 * date: 2019/12/15 19:59
 * description: 限流注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    /**
     * 资源名称，用于描述接口功能
     *
     * @return
     */
    String name() default "";

    /**
     * 资源 key
     *
     * @return
     */
    String key() default "";

    /**
     * key prefix
     *
     * @return
     */
    String prefix() default "";

    /**
     * 时间范围，单位秒
     *
     * @return
     */
    int period();

    /**
     * 限制访问次数
     *
     * @return
     */
    int count();

    /**
     * 限制类型
     *
     * @return
     */
    LimitType limitType() default LimitType.CUSTOMER;

}
