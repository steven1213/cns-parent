package com.steven.cns.infra.common.cache;

/**
 * @author steven.cao
 */
public interface CnsCache<K, V> {
    /**
     * 获取缓存
     *
     * @param key key
     * @return V
     */
    V get(K key);

    /**
     * 设置缓存
     *
     * @param key   key
     * @param value value
     */
    void put(K key, V value);

    /**
     * 设置缓存
     *
     * @param key    key
     * @param value  value
     * @param expire 过期时间
     */
    void put(K key, V value, long expire);

    /**
     * 设置缓存
     *
     * @param key       key
     * @param value     value
     * @param expire    过期时间
     * @param isRefresh 是否刷新过期时间
     */
    void put(K key, V value, long expire, boolean isRefresh);

    /**
     * 设置缓存
     *
     * @param key       key
     * @param value     value
     * @param expire    过期时间
     * @param isRefresh 是否刷新过期时间
     * @param isAsync   是否异步
     */
    void put(K key, V value, long expire, boolean isRefresh, boolean isAsync);

    /**
     * 移除缓存
     *
     * @param key key
     */
    void remove(K key);

    /**
     * 清空缓存
     */
    void clear();

}
