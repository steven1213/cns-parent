package com.steven.cns.infra.common.type;

import lombok.Getter;

/**
 * @author steven.cao
 */

@Getter
public enum RespResult implements BaseEnum {

    /**
     * success code.
     */
    SUCCESS(0, "success."),

    /**
     * failure code.
     */
    FAILURE(-1, "failure."),
    ;

    private final Integer code;

    private final String name;

    RespResult(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
