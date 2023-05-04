package com.steven.cns.infra.event;

import lombok.Data;

import java.io.Serializable;

/**
 * @author steven.cao
 */
@Data
public abstract class Payload<T> implements Serializable {

    private final T data;


    /**
     * get type
     *
     * @return type
     */
    public abstract String getType();

}
