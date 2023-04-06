package com.steven.cns.admin.infra.type;

import com.steven.cns.infra.common.type.BaseIntKeyEnum;
import lombok.Getter;

/**
 * @author steven.cao
 */

@Getter
public enum CommonStatusEnum implements BaseIntKeyEnum {

    /**
     * 正常 1
     */
    NORMAL(1, "正常"),

    /**
     * 锁定 0
     */
    LOCKED(0, "锁定"),
    ;

    private final Integer code;

    private final String value;

    CommonStatusEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
