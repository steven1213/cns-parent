package com.steven.cns.infra.common.utils;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

/**
 * jedis 工具类
 *
 * @author steven.cao
 */
@ConditionalOnProperty(name = "spring.redis.jedis.pool.enable", havingValue = "true", matchIfMissing = true)
@Component
public class JedisUtils {

//    @Autowired
    private JedisPool jedisPool;

    public Jedis getResource() {
        return jedisPool.getResource();
    }

    public void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.get(key);
        } finally {
            returnResource(jedis);
        }
    }

    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.set(key, value);
        } finally {
            returnResource(jedis);
        }
    }

    public void setex(String key, String value, int seconds) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.setex(key, seconds, value);
        } finally {
            returnResource(jedis);
        }
    }

    public void del(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.del(key);
        } finally {
            returnResource(jedis);
        }
    }

    public void incr(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.incr(key);
        } finally {
            returnResource(jedis);
        }
    }

    public void decr(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.decr(key);
        } finally {
            returnResource(jedis);
        }
    }

    public void expire(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.expire(key, seconds);
        } finally {
            returnResource(jedis);
        }
    }

    public void setList(String key, List<String> list) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            for (String value : list) {
                jedis.rpush(key, value);
            }
        } finally {
            returnResource(jedis);
        }
    }

    public List<String> getList(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.lrange(key, 0, -1);
        } finally {
            returnResource(jedis);
        }
    }

    public void setHash(String key, Map<String, String> map) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.hmset(key, map);
        } finally {
            returnResource(jedis);
        }
    }

    public Map<String, String> getHash(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hgetAll(key);
        } finally {
            returnResource(jedis);
        }
    }

    // add other methods ...
}
