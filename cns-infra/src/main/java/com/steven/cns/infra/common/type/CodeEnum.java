package com.steven.cns.infra.common.type;

public interface CodeEnum<T> {

    /**
     * @return
     */
    T getCode();

    /**
     * 获取value值
     *
     * @return String
     */
    String getValue();
}
