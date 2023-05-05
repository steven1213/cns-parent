package com.steven.cns.infra.common.cache;

import java.lang.reflect.InvocationTargetException;

/**
 * @author steven.cao
 */
public interface CnsCacheKeyGenerator {

    /**
     * 生成缓存key
     *
     * @param params 参数
     * @return String
     */
    String generateKey(Object... params);

    /**
     * 获取实例
     *
     * @return CnsCacheKeyGenerator
     */
    CnsCacheKeyGenerator newInstance() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
