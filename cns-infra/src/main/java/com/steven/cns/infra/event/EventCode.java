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
     * code code
     */
    private String code;

    /**
     * desc desc
     */
    private String desc;


    EventCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static EventCode fromCode(String code) {
        for (EventCode eventCode : EventCode.values()) {
            if (eventCode.getCode().equals(code)) {
                return eventCode;
            }
        }
        throw new IllegalArgumentException("unknown event code: " + code);
    }
}
