package com.steven.cns.infra.event;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author steven.cao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event<T> implements Serializable {

    private EventContext context;

    private EventCode code;

    private T payload;

}
