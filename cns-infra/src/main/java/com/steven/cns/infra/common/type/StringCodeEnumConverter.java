package com.steven.cns.infra.common.type;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author dr.panda
 */
@Component
public class StringCodeEnumConverter<T extends CodeEnum<String>> implements Converter<String, T> {
    private final Class<T> enumType;

    public StringCodeEnumConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public T convert(String source) {
        return CodeEnumUtil.getByCode(enumType, source);
    }
}
