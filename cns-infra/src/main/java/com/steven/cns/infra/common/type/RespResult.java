package com.steven.cns.infra.common.type;

import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * @author steven.cao
 */
@Getter
public enum RespResult implements StringCodeEnum {

    /**
     * success code.
     */
    SUCCESS("200", "success."),

    /**
     * server error code.
     */
    SERVER_ERROR("500", "server error."),

    /**
     * bad request code.
     */
    BAD_REQUEST("400", "bad request."),

    /**
     * unauthorized code.
     */
    UNAUTHORIZED("401", "unauthorized."),

    /**
     * forbidden code.
     */
    FORBIDDEN("403", "forbidden."),

    /**
     * not found code.
     */
    NOT_FOUND("404", "not found."),

    /**
     * method not allowed code.
     */
    METHOD_NOT_ALLOWED("405", "method not allowed."),

    /**
     * not acceptable code.
     */
    NOT_ACCEPTABLE("406", "not acceptable."),

    /**
     * request timeout code.
     */
    REQUEST_TIMEOUT("408", "request timeout."),

    /**
     * conflict code.
     */
    CONFLICT("409", "conflict."),


    /**
     * failure code.
     */
    FAILURE("-1", "failure."),
    ;

    private final String code;

    private final String value;

    RespResult(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
