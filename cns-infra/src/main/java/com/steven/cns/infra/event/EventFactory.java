package com.steven.cns.infra.event;

/**
 * @author steven.cao
 */
public interface EventFactory<T> {
    /**
     * create event
     *
     * @param eventCode eventCode
     * @param payload   payload
     * @param context   context
     * @return event
     */
    Event<T> createEvent(EventCode eventCode, T payload, EventContext context);
}
