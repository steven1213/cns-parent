package com.steven.cns.infra.common.client.feign;

import com.steven.cns.infra.common.constant.CnsConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author steven.cao
 */
public class FeignClientTraceIdInterceptor implements RequestInterceptor {

    private final String traceId;

    public FeignClientTraceIdInterceptor(String traceId) {
        this.traceId = traceId;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(CnsConstant.TRACE_ID, traceId);
    }
}
