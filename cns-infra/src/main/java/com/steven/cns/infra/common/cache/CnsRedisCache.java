package com.steven.cns.infra.common.cache;

/**
 * redis cache
 *
 * @param <K>
 * @param <V>
 */
public class CnsRedisCache<K, V> implements CnsCache<K, V> {

    private final String cacheName;

    public CnsRedisCache(String cacheName) {
        this.cacheName = cacheName;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void put(K key, V value) {

    }

    @Override
    public void put(K key, V value, long expire) {

    }

    @Override
    public void put(K key, V value, long expire, boolean isRefresh) {

    }

    @Override
    public void put(K key, V value, long expire, boolean isRefresh, boolean isAsync) {

    }

    @Override
    public void remove(K key) {

    }

    @Override
    public void clear() {

    }
}
