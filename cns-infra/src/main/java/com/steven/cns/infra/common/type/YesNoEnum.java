package com.steven.cns.infra.common.type;

import lombok.Getter;

/**
 * @author steven.cao
 */

@Getter
public enum YesNoEnum implements BaseEnum {

    /**
     * 否.
     */
    NO(0, "否"),

    /**
     * 是.
     */
    YES(1, "是"),


    ;

    private Integer code;

    private String name;

    YesNoEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
