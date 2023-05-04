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
     *
     * @return String
     */
    public static String setTraceIdIfAbsent() {
        String traceId = MDC.get(CnsConstant.TRACE_ID);
        if (StringUtils.isBlank(traceId)) {
            traceId = UUID.randomUUID().toString();
            MDC.put(CnsConstant.TRACE_ID, traceId);
        }
        return traceId;
    }

    /**
     * 获取 traceId
     *
     * @return String
     */
    public static String getTraceId() {
        return MDC.get(CnsConstant.TRACE_ID);
    }

    /**
     * wrap callable
     *
     * @param callable callable
     * @param context  context
     * @param <T>      T
     * @return Callable
     */
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

    /**
     * wrap runnable
     *
     * @param runnable runnable
     * @param context  context
     * @return Runnable
     */
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
