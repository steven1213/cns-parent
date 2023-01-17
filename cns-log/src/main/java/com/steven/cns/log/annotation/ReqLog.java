package com.steven.cns.log.annotation;

import java.lang.annotation.*;

/**
 * @author dr.panda
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ReqLog {
}
