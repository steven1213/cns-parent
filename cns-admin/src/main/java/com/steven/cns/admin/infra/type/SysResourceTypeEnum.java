package com.steven.cns.admin.infra.type;

import com.steven.cns.infra.common.type.CodeEnum;
import lombok.Getter;

/**
 * @author steven.cao
 */

@Getter
public enum SysResourceTypeEnum implements CodeEnum<Integer> {
    /**
     * resource type
     */
    RESOURCE_MENU(0, "菜单"),

    RESOURCE_BUTTON(1, "按钮"),
    ;


    SysResourceTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    private Integer code;


    private String value;
}
