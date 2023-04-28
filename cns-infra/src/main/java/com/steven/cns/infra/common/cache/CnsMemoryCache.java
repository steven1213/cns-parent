package com.steven.cns.infra.common.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author steven.cao
 */
public class CnsMemoryCache<K, V> implements CnsCache<K, V> {

    private final Map<K, V> cacheMap = new ConcurrentHashMap<>();

    @Override
    public V get(K key) {
        return cacheMap.get(key);
    }

    @Override
    public void put(K key, V value) {
        cacheMap.put(key, value);
    }

    @Override
    public void put(K key, V value, long expire) {
        // 不支持
    }

    @Override
    public void put(K key, V value, long expire, boolean isRefresh) {
        // 不支持
    }

    @Override
    public void put(K key, V value, long expire, boolean isRefresh, boolean isAsync) {
        // 不支持
    }

    @Override
    public void remove(K key) {
        cacheMap.remove(key);
    }

    @Override
    public void clear() {
        cacheMap.clear();
    }
}
