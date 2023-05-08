package com.steven.cns.infra.common.type;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author steven.cao
 */
@Configuration
public class EnumConverterConfiguration {

    @Resource
    GenericCodeEnumConverter genericCodeEnumConverter;

    @Bean
    public ConversionService conversionService() {
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        factory.setConverters(Set.of(genericCodeEnumConverter));
        return factory.getObject();
    }
}