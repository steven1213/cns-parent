package com.steven.cns.infra.common.type;

/**
 * @author dr.panda
 */
public class CodeEnumUtil {
    private CodeEnumUtil() {
    }

    /**
     * 通过 code 获取对应的枚举
     *
     * @param enumType 枚举类型
     * @param code     枚举的 code 值
     * @param <T>      枚举泛型
     * @return 枚举值
     */
    public static <T extends CodeEnum<?>> T getByCode(Class<T> enumType, Object code) {
        if (code == null) {
            return null;
        }
        if (!CodeEnum.class.isAssignableFrom(enumType)) {
            throw new IllegalArgumentException("Class [" + enumType.getName() + "] must implement interface [CodeEnum]");
        }

        for (T e : enumType.getEnumConstants()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }

        throw new IllegalArgumentException("Cannot convert " + code + " to " + enumType.getSimpleName() + " by code value.");
    }
}






