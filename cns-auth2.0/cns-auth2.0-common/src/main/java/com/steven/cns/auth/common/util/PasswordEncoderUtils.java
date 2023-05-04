package com.steven.cns.auth.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author steven.cao
 */
public final class PasswordEncoderUtils {
    private PasswordEncoderUtils() {
    }

    public static String encode(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(rawPassword);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, encodedPassword);
    }
}
