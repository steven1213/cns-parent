package com.steven.cns.infra.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * jedis 工具类
 *
 * @author steven.cao
 */
public final class JedisUtils {

    private static JedisPool jedisPool;

    public static void init(JedisPool jedisPool) {
        JedisUtils.jedisPool = jedisPool;
    }

    public static Jedis getResource() {
        return jedisPool.getResource();
    }

    public static void returnResource(Jedis jedis) {
        jedis.close();
    }

    public static void set(String key, String value) {
        try (Jedis jedis = getResource()) {
            jedis.set(key, value);
        }
    }

    public static String get(String key) {
        try (Jedis jedis = getResource()) {
            return jedis.get(key);
        }
    }

    public static void del(String key) {
        try (Jedis jedis = getResource()) {
            jedis.del(key);
        }
    }

}
