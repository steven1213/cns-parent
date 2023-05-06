package com.steven.cns.infra.common.type;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author dr.panda
 */
@Component
public class IntegerCodeEnumConverter<T extends CodeEnum<Integer>> implements Converter<Integer, T> {
    private final Class<T> enumType;

    public IntegerCodeEnumConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public T convert(Integer source) {
        return CodeEnumUtil.getByCode(enumType, source);
    }
}
