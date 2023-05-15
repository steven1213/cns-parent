package com.steven.cns.infra.common.cache.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author steven.cao
 */
@Configuration
public class CnsJedisConfig {

    @Resource
    private CnsRedisProperties cnsRedisProperties;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(cnsRedisProperties.getPool().getMaxIdle());
        poolConfig.setMinIdle(cnsRedisProperties.getPool().getMinIdle());
        poolConfig.setMaxTotal(cnsRedisProperties.getPool().getMaxTotal());
        poolConfig.setTestOnBorrow(cnsRedisProperties.getPool().isTestOnBorrow());
        poolConfig.setTestOnReturn(cnsRedisProperties.getPool().isTestOnReturn());
        poolConfig.setTestWhileIdle(cnsRedisProperties.getPool().isTestWhileIdle());
        poolConfig.setNumTestsPerEvictionRun(cnsRedisProperties.getPool().getNumTestsPerEvictionRun());

        String mode = cnsRedisProperties.getMode();
        if (RedisModelEnum.STANDALONE.getMode().equalsIgnoreCase(mode)) {
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(cnsRedisProperties.getHost(), cnsRedisProperties.getPort());
            return new JedisConnectionFactory(redisStandaloneConfiguration, JedisClientConfiguration.builder().usePooling().poolConfig(poolConfig).build());
        } else if (RedisModelEnum.MASTER_SLAVE.getMode().equalsIgnoreCase(mode)) {
            RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration();
            sentinelConfig.master(cnsRedisProperties.getMaster().getName());
            sentinelConfig.sentinel(cnsRedisProperties.getMaster().getHost(), cnsRedisProperties.getMaster().getPort());
            sentinelConfig.sentinel(cnsRedisProperties.getSlave().getHost(), cnsRedisProperties.getSlave().getPort());
            return new JedisConnectionFactory(sentinelConfig);
        } else if (RedisModelEnum.SENTINEL.getMode().equalsIgnoreCase(mode)) {
            // Sentinel mode
            RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration(cnsRedisProperties.getSentinel().getMaster(), new HashSet<>(Arrays.asList(cnsRedisProperties.getSentinel().getNodes().split(","))));
            return new JedisConnectionFactory(sentinelConfig, JedisClientConfiguration.builder().usePooling().poolConfig(poolConfig).build());
        } else if (RedisModelEnum.CLUSTER.getMode().equalsIgnoreCase(mode)) {
            // Cluster mode
            RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration(Arrays.asList(cnsRedisProperties.getCluster().getNodes().split(",")));
            return new JedisConnectionFactory(clusterConfig, JedisClientConfiguration.builder().usePooling().poolConfig(poolConfig).build());
        } else {
            throw new IllegalArgumentException("Invalid Redis mode: " + mode);
        }
    }
}
