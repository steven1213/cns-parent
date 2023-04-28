package com.steven.cns.infra.common.type;

import lombok.Getter;

/**
 * @author steven.cao
 */

@Getter
public enum GenderEnum implements BaseIntKeyEnum {
    /**
     * 0-女性
     */
    FEMALE(0, "female"),
    /**
     * 1-男性
     */
    MALE(1, "male"),

    /**
     * 其他
     */
    OTHER(2, "other"),

    ;

    private final Integer code;

    private final String value;

    GenderEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
