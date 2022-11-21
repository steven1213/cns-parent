package com.steven.cns.infra.common.utils;

import com.steven.cns.infra.common.constant.CnsConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @author steven.cao
 */
public final class MdcUtils {

    private MdcUtils() {
    }

    /**
     * 如果不存在则设置traceId.
     */
    public static void setTraceIdIfAbsent() {
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

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (null == context) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }

            setTraceIdIfAbsent();

            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (null == context) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }

            setTraceIdIfAbsent();

            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }

}
