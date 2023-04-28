package com.steven.cns.infra.event;

/**
 * @author steven.cao
 */
public interface EventHandler<T extends Event, R> {

    /**
     * handle event
     *
     * @param event event
     * @return result
     */
    R handle(T event);
}
