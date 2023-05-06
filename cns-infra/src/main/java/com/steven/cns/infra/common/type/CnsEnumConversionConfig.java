package com.steven.cns.infra.common.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import java.util.List;

/**
 * @author dr.panda
 */
@Configuration
public class CnsEnumConversionConfig {
    @Autowired
    private List<StringCodeEnumConverter<?>> stringCodeEnumConverters;

    @Autowired
    private List<IntegerCodeEnumConverter<?>> integerCodeEnumConverters;

    @Bean
    public ConversionService conversionService() {
        GenericConversionService conversionService = new GenericConversionService();
        stringCodeEnumConverters.forEach(conversionService::addConverter);
        integerCodeEnumConverters.forEach(conversionService::addConverter);
        return conversionService;
    }
}
