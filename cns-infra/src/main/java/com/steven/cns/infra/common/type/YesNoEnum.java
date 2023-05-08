package com.steven.cns.infra.common.type;

import lombok.Getter;

/**
 * @author steven.cao
 */
@Getter
public enum YesNoEnum implements IntegerCodeEnum {

    /**
     * 否.
     */
    NO(0, "否"),

    /**
     * 是.
     */
    YES(1, "是"),


    ;

    private final Integer code;

    private final String value;

    YesNoEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
