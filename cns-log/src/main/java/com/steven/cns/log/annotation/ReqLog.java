package com.steven.cns.log.annotation;

import java.lang.annotation.*;

/**
 * @author steven.cao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ReqLog {

    /**
     * 是否打印request header 默认不打印，若打印需设置为true
     * @return
     */
    boolean printHeader() default false;
}
