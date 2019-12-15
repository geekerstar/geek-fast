package com.geekerstar.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author geekerstar
 * date: 2019/12/15 19:34
 * description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Helper {

    @AliasFor(annotation = Component.class)
    String value() default "";
}
