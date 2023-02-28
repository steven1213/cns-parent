package com.steven.cns.infra.common.annotation.api;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @author steven.cao
 */
public class ApiHandlerMapping extends RequestMappingHandlerMapping {
    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        return buildFrom(AnnotationUtils.findAnnotation(handlerType, ApiVersion.class));
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        return buildFrom(AnnotationUtils.findAnnotation(method, ApiVersion.class));
    }

    private ApiCondition buildFrom(ApiVersion apiVersion) {
        return null == apiVersion ? getDefaultCondition() : new ApiCondition(ApiConverter.convert(apiVersion.version()));
    }

    private ApiCondition getDefaultCondition() {
        return new ApiCondition(ApiConverter.convert(ApiVersionConstant.DEFAULT_VERSION), true);
    }
}
