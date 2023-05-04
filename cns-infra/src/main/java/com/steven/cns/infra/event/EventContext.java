package com.steven.cns.infra.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author steven.cao
 */
@Data
@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class EventContext implements Serializable  {

    private final Map<String,Object> attributes = new HashMap<>();
}
