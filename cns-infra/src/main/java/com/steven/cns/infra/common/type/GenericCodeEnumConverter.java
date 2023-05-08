package com.steven.cns.infra.common.type;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;

/**
 * @author steven.cao
 */
@Component
public class GenericCodeEnumConverter<T extends CodeEnum<?>> implements Converter<String, T> {
    @Override
    public T convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        // 获取泛型类型的实际类
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> enumType = (Class<T>) parameterizedType.getActualTypeArguments()[0];


        if (IntegerCodeEnum.class.isAssignableFrom(enumType)) {
            return CodeEnumUtil.getByCode(enumType, Integer.valueOf(source));
        } else if (StringCodeEnum.class.isAssignableFrom(enumType)) {
            return CodeEnumUtil.getByCode(enumType, source);
        } else {
            throw new UnsupportedOperationException("unsupported code type");
        }
    }
}
