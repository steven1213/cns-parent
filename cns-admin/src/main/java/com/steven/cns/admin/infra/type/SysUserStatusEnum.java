package com.steven.cns.admin.infra.type;

import com.steven.cns.infra.common.type.CodeEnum;
import lombok.Getter;

/**
 * @author steven.cao
 */

@Getter
public enum SysUserStatusEnum implements CodeEnum<Integer> {

    /**
     * 未激活
     */
    NOT_ACTIVATED(0, "未激活"),

    /**
     * 正常
     */
    NORMAL(1, "正常"),

    /**
     * 锁定
     */
    LOCKED(2, "锁定"),

    /**
     * 注销
     */
    UNSUBSCRIBE(3, "注销"),
    ;

    private Integer code;

    private String value;

    SysUserStatusEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
