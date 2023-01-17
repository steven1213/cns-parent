package com.steven.cns.admin.infra.type;

import com.steven.cns.infra.common.type.BaseEnum;
import lombok.Getter;

/**
 * @author steven.cao
 */

@Getter
public enum CommonStatusEnum implements BaseEnum {

    /**
     * 正常 1
     */
    NORMAL(1, "正常"),

    /**
     * 锁定 0
     */
    LOCKED(0, "锁定"),
    ;

    private Integer code;

    private String value;

    CommonStatusEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
