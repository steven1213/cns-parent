package com.steven.cns.infra.common.annotation.api;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author steven.cao
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ApiAutoConfiguration.class)
public @interface EnableApiVersion {
}
