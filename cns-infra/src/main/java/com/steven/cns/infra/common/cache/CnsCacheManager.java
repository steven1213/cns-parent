package com.steven.cns.infra.common.cache;

/**
 * @author steven.cao
 */
public interface CnsCacheManager {

    /**
     * 获取缓存
     *
     * @param name name
     * @param <K>  key
     * @param <V>  value
     * @return CnsCache
     */
    <K, V> CnsCache<K, V> getCache(String name);
}
