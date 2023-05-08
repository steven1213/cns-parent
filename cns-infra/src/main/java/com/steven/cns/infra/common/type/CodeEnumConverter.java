package com.steven.cns.infra.common.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author steven.cao
 * useage:
 * @Data
 * @Entity
 * @Table(name = "user")
 * public class User {
 *
 *     @Id
 *     @GeneratedValue(strategy = GenerationType.IDENTITY)
 *     private Long id;
 *
 *     private String name;
 *
 *     @Column(name = "gender")
 *     @Convert(converter = CodeEnumConverter.class)
 *     private GenderEnum gender;
 *
 *     @Column(name = "status")
 *     @Convert(converter = CodeEnumConverter.class)
 *     private StatusEnum status;
 *
 * }
 */
@Converter
public class CodeEnumConverter implements AttributeConverter<CodeEnum<?>, Object> {

    @Override
    public Object convertToDatabaseColumn(CodeEnum<?> attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getCode();
    }

    @SuppressWarnings("unchecked")
    @Override
    public CodeEnum<?> convertToEntityAttribute(Object dbData) {
        if (dbData == null) {
            return null;
        }
        CodeEnum<?>[] enums = (CodeEnum<?>[]) ((CodeEnum<?>) dbData).getClass().getEnumConstants();
        for (CodeEnum<?> e : enums) {
            if (e.getCode().equals(dbData)) {
                return e;
            }
        }
        throw new UnsupportedOperationException("枚举转换异常");
    }
}
