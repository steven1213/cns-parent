package com.steven.cns.infra.common.cache.annotation;

import com.steven.cns.infra.common.cache.CnsCacheKeyGenerator;
import com.steven.cns.infra.common.cache.DefaultCnsCacheKeyGenerator;

import java.lang.annotation.*;

/**
 * @author steven.cao
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CnsCacheable {

    /**
     * 缓存默认过期时间为0，即不过期
     */
    int expiredAfterWriteSeconds() default 0;

    /**
     * 缓存key生成器
     */
    Class<? extends CnsCacheKeyGenerator> keyGenerator() default DefaultCnsCacheKeyGenerator.class;

    /**
     * 缓存key前缀
     */
    String keyPrefix() default "";

}
