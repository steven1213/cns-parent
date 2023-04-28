package com.steven.cns.infra.event;

import lombok.*;

import java.io.Serializable;

/**
 * @author steven.cao
 */
@Getter
public enum EventCode {

    /**
     * default event code
     */
    DEFAULT("DEFAULT", "DEFAULT"),

    ;

    /**
     * module name
     */
    private String module;

    /**
     * event name
     */
    private String name;


    EventCode(String module, String name) {
        this.module = module;
        this.name = name;
    }
}
