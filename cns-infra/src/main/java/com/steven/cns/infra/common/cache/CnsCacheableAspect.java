package com.steven.cns.infra.common.cache;

import com.steven.cns.infra.common.cache.annotation.CnsCacheable;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author steven.cao
 */
@Slf4j
@Aspect
@Component
public class CnsCacheableAspect {

    @Pointcut("@annotation(com.steven.cns.infra.common.cache.annotation.CnsCacheable)")
    public void cacheMethod() {
    }

    @Around("cacheMethod()")
    public Object cacheableMethodAround(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        CnsCacheable annotation = method.getAnnotation(CnsCacheable.class);
        if (null == annotation) {
            return pjp.proceed();
        }
        Object[] params = pjp.getArgs();
        CnsCacheKeyGenerator keyGenerator;
        try {
            keyGenerator = annotation.keyGenerator().getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        String key = keyGenerator.generateKey(params);
        if (annotation.keyPrefix().length() > 0) {
            key = annotation.keyPrefix() + ":" + key;
        }
        log.info("cache key: {}", key);
        // 从缓存中获取数据 TODO
//        Object cacheData = CnsCacheManager.getCache(key).get(key);
        Object cacheData = null;
        log.info("cache data: {}", cacheData);
        if (null != cacheData) {
            return cacheData;
        }
        Object result = pjp.proceed();
        if (null != result) {
            // 将数据放入缓存 TODO
//            CnsCache.put(key, result, annotation.expiredAfterWriteSeconds(), TimeUnit.SECONDS);
        }
        return result;
    }
}
