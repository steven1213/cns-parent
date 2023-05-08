package com.steven.cns.infra.common.type;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author steven.cao
 */
public final class CodeEnumUtil {
    private static final Map<Class<? extends CodeEnum<?>>, Map<Object, ? extends CodeEnum<?>>> CODE_ENUM_MAP = new ConcurrentHashMap<>();

    private CodeEnumUtil() {
        throw new UnsupportedOperationException();
    }

    public static <T extends CodeEnum<?>> T getByCode(Class<T> enumClass, Object code) {
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException(enumClass + " is not an enum type");
        }

        if (!CODE_ENUM_MAP.containsKey(enumClass)) {
            synchronized (enumClass) {
                if (!CODE_ENUM_MAP.containsKey(enumClass)) {
                    Map<Object, ? extends CodeEnum<?>> map = Arrays.stream(enumClass.getEnumConstants())
                            .collect(Collectors.toMap(CodeEnum::getCode, Function.identity()));
                    CODE_ENUM_MAP.put(enumClass, map);
                }
            }
        }
        return (T) CODE_ENUM_MAP.get(enumClass).get(code);
    }
}






