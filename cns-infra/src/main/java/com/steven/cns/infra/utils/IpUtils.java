package com.steven.cns.infra.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author steven.cao
 */
public final class IpUtils {

    private static final String UNKNOWN_IP = "unknown";

    private IpUtils() {
    }

    /**
     * 获取真实ip
     *
     * @param request 请求
     * @return 真实ip
     */
    public static String getRealIp(HttpServletRequest request) {
        String realIp = request.getHeader("X-Forwarded-For");
        if (realIp == null || realIp.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(realIp)) {
            realIp = request.getHeader("Proxy-Client-IP");
        }
        if (realIp == null || realIp.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(realIp)) {
            realIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (realIp == null || realIp.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(realIp)) {
            realIp = request.getHeader("HTTP_CLIENT_IP");
        }
        if (realIp == null || realIp.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(realIp)) {
            realIp = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (realIp == null || realIp.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(realIp)) {
            realIp = request.getRemoteAddr();
        }
        return realIp;
    }

    /**
     * 将String型ip转换为long
     *
     * @param ip String型ip
     * @return long型ip
     */
    public static Long convertIpToLong(String ip) {
        String[] ipArray = ip.split("\\.");
        return Long.parseLong(ipArray[0]) << 24 | Long.parseLong(ipArray[1]) << 16 | Long.parseLong(ipArray[2]) << 8 | Long.parseLong(ipArray[3]);
    }

    /**
     * 将long型ip转换为String
     *
     * @param ip long型ip
     * @return String型ip
     */
    public static String convertIpToString(Long ip) {
        return (ip >> 24 & 0xFF) + "." + (ip >> 16 & 0xFF) + "." + (ip >> 8 & 0xFF) + "." + (ip & 0xFF);
    }
}
