package com.steven.cns.infra.event;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author steven.cao
 */
@Data
public class Event<T> implements Serializable {

    private final LocalDateTime timestamp;

    private final EventCode eventCode;

    private final T payload;

    private final EventContext context;

    public Event(EventCode eventCode, T payload, EventContext context) {
        this.timestamp = LocalDateTime.now();
        this.eventCode = eventCode;
        this.context = context;
        this.payload = payload;
    }
}
