package com.steven.cns.infra.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author steven.cao
 */
@Slf4j
public final class ServletUtils {
    private ServletUtils() {
    }

    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    public static String getParameter(String name, String defaultValue) {
        Object parameterValue = getRequest().getParameter(name);
        return Objects.isNull(parameterValue) ? defaultValue : parameterValue.toString();
    }

    public static Integer getParameterToInt(String name) {
        return getParameterToInt(name, null);
    }

    public static Integer getParameterToInt(String name, Integer defaultValue) {
        Object parameterValue = getRequest().getParameter(name);
        return Objects.isNull(parameterValue) ? defaultValue : Integer.parseInt(parameterValue.toString());
    }

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static String renderString(HttpServletResponse response, String str) {
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(str);
        } catch (IOException ex) {
            log.error("render string error.", ex);
        }
        return null;
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }
}
