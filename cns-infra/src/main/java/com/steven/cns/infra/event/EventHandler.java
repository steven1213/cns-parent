package com.steven.cns.infra.event;

/**
 * @author steven.cao
 */
public interface EventHandler<T, R> {

    /**
     * handle event
     *
     * @param event event
     * @return result
     */
    R handleEvent(Event<T> event);
}
