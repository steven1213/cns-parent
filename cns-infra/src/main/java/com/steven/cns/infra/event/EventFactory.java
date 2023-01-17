package com.steven.cns.infra.event;

/**
 * @author steven.cao
 */
public interface EventFactory<T extends Event> {
    /**
     * 返回相应的的实例
     *
     * @return T
     */
    T newInstance();
}
