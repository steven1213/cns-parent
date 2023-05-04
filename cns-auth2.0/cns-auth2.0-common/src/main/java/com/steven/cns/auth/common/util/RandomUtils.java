package com.steven.cns.auth.common.util;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author steven.cao
 */
public final class RandomUtils {
    private static final SecureRandom RANDOM = new SecureRandom();

    private RandomUtils() {
    }

    public static String generateString(int length) {
        byte[] bytes = new byte[length];
        RANDOM.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public static String generateToken(int length) {
        byte[] bytes = new byte[length];
        RANDOM.nextBytes(bytes);
        return Base64.getEncoder().withoutPadding().encodeToString(bytes);
    }
}
