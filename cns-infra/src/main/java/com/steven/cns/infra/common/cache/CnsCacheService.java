package com.steven.cns.infra.common.cache;

/**
 * @author steven.cao
 */
public class CnsCacheService {

    private final CnsCache<String, Object> cache;

    public CnsCacheService(CnsCacheManager cacheManager) {
        this.cache = cacheManager.getCache("cnsCache");
    }

    public Object get(String key) {
        return cache.get(key);
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public void put(String key, Object value, long expire) {
        cache.put(key, value, expire);
    }

    public void put(String key, Object value, long expire, boolean isRefresh) {
        cache.put(key, value, expire, isRefresh);
    }

    public void put(String key, Object value, long expire, boolean isRefresh, boolean isAsync) {
        cache.put(key, value, expire, isRefresh, isAsync);
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }
}
