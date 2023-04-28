package com.steven.cns.infra.event;

/**
 * @author steven.cao
 */
public class EventFactory {
    public static <T> Event<T> createEvent(EventCode code, T payload) {
        return Event.<T>builder()
                .code(code)
                .payload(payload)
                .build();
    }
}
