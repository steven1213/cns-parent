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

    private final String value;

    RespResult(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
