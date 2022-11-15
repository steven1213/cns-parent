package com.steven.cns.infra.common.utils;

import com.steven.cns.infra.common.constant.CnsConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * @author steven.cao
 */
public final class MdcUtils {

    private MdcUtils() {
    }

    /**
     * 如果不存在则设置traceId.
     */
    public static void setIfAbsent() {
        String traceId = MDC.get(CnsConstant.TRACE_ID);
        if (StringUtils.isBlank(traceId)) {
            MDC.put(CnsConstant.TRACE_ID, UUID.randomUUID().toString());
        }
    }

    /**
     * 获取 traceId
     *
     * @return String
     */
    public static String getTraceId() {
        return MDC.get(CnsConstant.TRACE_ID);
    }

}
