package com.steven.cns.infra.event;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author steven.cao
 */
@Data
public class EventContext implements Serializable {

    private final Map<String, Object> metadata = new HashMap<>();

    private final LocalDateTime timestamp;

    public EventContext() {
        this.timestamp = LocalDateTime.now();
    }

    public EventContext(Map<String, Object> metadata) {
        this.timestamp = LocalDateTime.now();
        this.metadata.putAll(metadata);
    }

    public void addMetadata(String key, Object value) {
        metadata.put(key, value);
    }

    public Object getMetadata(String key) {
        return metadata.get(key);
    }

    public <T> T getMetadata(String key, Class<T> clazz) {
        return clazz.cast(metadata.get(key));
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getAllMetadata() {
        return Collections.unmodifiableMap(metadata);
    }
}
