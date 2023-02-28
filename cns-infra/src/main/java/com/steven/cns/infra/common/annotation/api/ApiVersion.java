package com.steven.cns.infra.common.annotation.api;

import java.lang.annotation.*;

/**
 * @author steven.cao
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {

    /**
     * 版本 x.y.z 格式
     *
     * @return -
     */
    String version() default "1.0.0";
}
