package com.steven.cns.infra.common.cache.redis;

import lombok.Getter;

/**
 * @author steven.cao
 */

@Getter
public enum RedisModelEnum {
    /**
     * 单机模式
     */
    STANDALONE("standalone", "单机模式"),

    /**
     * 主从模式
     */
    MASTER_SLAVE("master_slave", "主从模式"),

    /**
     * 哨兵模式
     */
    SENTINEL("sentinel", "哨兵模式"),

    /**
     * 集群模式
     */
    CLUSTER("cluster", "集群模式"),

    ;

    private final String mode;

    private final String desc;

    RedisModelEnum(String mode, String desc) {
        this.mode = mode;
        this.desc = desc;
    }
}
