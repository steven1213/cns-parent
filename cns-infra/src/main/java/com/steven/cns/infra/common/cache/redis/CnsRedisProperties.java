package com.steven.cns.infra.common.cache.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author steven.cao
 */
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@ConditionalOnProperty(prefix = CnsRedisProperties.PREFIX, name = "mode", matchIfMissing = true)
@ConfigurationProperties(prefix = CnsRedisProperties.PREFIX)
public class CnsRedisProperties {

    public static final String PREFIX = "cns.redis";

    private String mode;

    private String host;

    private int port;

    private MasterSlave master;

    private MasterSlave slave;

    private Sentinel sentinel;

    private Cluster cluster;

    private CnsRedisPool pool;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CnsRedisPool {
        private int maxIdle = 8;

        private int minIdle = 0;

        private int maxTotal = 100;

        private int maxWaitMillis = 10000;

        private boolean testOnBorrow = true;

        private boolean testOnReturn = true;

        private boolean testWhileIdle = true;

        private int timeBetweenEvictionRunsMillis = 30000;

        private int numTestsPerEvictionRun = -1;

        private int minEvictableIdleTimeMillis;

        private int softMinEvictableIdleTimeMillis;

        private boolean blockWhenExhausted;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MasterSlave {

        private String name;
        private String host;

        private int port;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Sentinel {

        private String master;

        private String nodes;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Cluster {

        private String nodes;
    }
}
