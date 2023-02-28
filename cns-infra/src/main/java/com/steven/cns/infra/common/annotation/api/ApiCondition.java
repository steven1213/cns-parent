package com.steven.cns.infra.common.annotation.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

/**
 * @author steven.cao
 */
@Slf4j
public class ApiCondition implements RequestCondition<ApiCondition> {

    public static ApiCondition empty = new ApiCondition(ApiConverter.convert(ApiVersionConstant.DEFAULT_VERSION));

    private ApiItem apiItem;

    private boolean NULL;

    public ApiCondition(ApiItem apiItem) {
        this.apiItem = apiItem;
    }

    public ApiCondition(ApiItem apiItem, boolean NULL) {
        this.apiItem = apiItem;
        this.NULL = NULL;
    }

    @Override
    public ApiCondition combine(ApiCondition other) {
        if (other.NULL) {
            return this;
        }
        return other;
    }

    @Override
    public ApiCondition getMatchingCondition(HttpServletRequest request) {
        if (CorsUtils.isPreFlightRequest(request)) {
            return empty;
        }
        String version = request.getHeader(ApiVersionConstant.API_VERSION);
        if (StringUtils.isBlank(version)) {
            log.warn("未指定版本,默认使用{}版本", ApiVersionConstant.DEFAULT_VERSION);
            version = ApiVersionConstant.DEFAULT_VERSION;
        }
        ApiItem apiItem = ApiConverter.convert(version);
        if (apiItem.compareTo(ApiItem.API_ITEM_DEFAULT) < 0) {
            throw new IllegalArgumentException(MessageFormat.format("API版本错误{},最低版本为:{}", version, ApiVersionConstant.DEFAULT_VERSION));
        }

        if (apiItem.compareTo(ApiItem.API_ITEM_DEFAULT) > 0) {
            return this;
        }
        return null;
    }

    @Override
    public int compareTo(ApiCondition other, HttpServletRequest request) {
        int compare = other.apiItem.compareTo(this.apiItem);
        if (compare == 0) {
            log.warn("RequestMappingInfo相同,请检查!version:{}", other.apiItem);
        }
        return compare;
    }
}
