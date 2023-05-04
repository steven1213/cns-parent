package com.steven.cns.auth.common.util;

import java.security.SecureRandom;

/**
 * @author steven.cao
 */
public final class RandomValueStringGenerator {

    private static final String DEFAULT_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int DEFAULT_LENGTH = 18;

    private final SecureRandom random;
    private final char[] characters;
    private final int length;

    public RandomValueStringGenerator() {
        this(DEFAULT_LENGTH, DEFAULT_CHARACTERS);
    }

    public RandomValueStringGenerator(int length) {
        this(length, DEFAULT_CHARACTERS);
    }

    public RandomValueStringGenerator(int length, String characters) {
        this.length = length;
        this.characters = characters.toCharArray();
        this.random = new SecureRandom();
    }

    public String generate() {
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = characters[random.nextInt(characters.length)];
        }
        return new String(result);
    }
}
