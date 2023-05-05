package com.steven.cns.infra.common.cache;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author steven.cao
 */
@Slf4j
public class DefaultCnsCacheKeyGenerator implements CnsCacheKeyGenerator {

    @Override
    public String generateKey(Object... params) {
        if (params == null || params.length == 0) {
            return "no-args";
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(Arrays.toString(params).getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            log.error("generateKey error", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public CnsCacheKeyGenerator newInstance() throws InstantiationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return getClass().getDeclaredConstructor().newInstance();
    }
}
