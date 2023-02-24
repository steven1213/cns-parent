package com.steven.cns.log.annotation;

import java.lang.annotation.*;

/**
 * @author steven.cao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ReqLog {
    boolean printHeader() default false;
}
