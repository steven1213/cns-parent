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

    private EventContext eventContext;

    private EventCode eventCode;
    /**
     * 有错误或是有警告时的信息
     */
    private String message;

    private Boolean hasError = Boolean.FALSE;

    private Boolean hasWarning = Boolean.FALSE;
}
