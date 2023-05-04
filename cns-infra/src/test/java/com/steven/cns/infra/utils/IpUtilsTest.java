package com.steven.cns.infra.utils;

import com.steven.cns.infra.common.utils.IpUtils;
import junit.framework.TestCase;

public class IpUtilsTest extends TestCase {

    public void testConvertIpToLong() {
        long result = IpUtils.convertIpToLong("123.11.123.4");
        assertEquals(2064349956L, result);
    }

    public void testConvertIpToString() {
        String result = IpUtils.convertIpToString(2064349956L);
        assertEquals("123.11.123.4", result);
    }
}