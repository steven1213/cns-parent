package com.steven.cns.log.annotation;

import java.lang.annotation.*;

/**
 * @author steven.cao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface OperationLog {
    /**
     * appName appName
     *
     * @return -
     */
    String appName() default "" ;

    /**
     * module 模块
     *
     * @return module
     */
    String module() default "";

    /**
     * 描述
     *
     * @return description
     */
    String description() default "";
}
