package com.steven.cns.infra.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author dr.panda
 */
public final class GsonUtils {

    private GsonUtils() {
    }

    public static <T> T fromJson(String str, Class<T> clazz) {
        return new Gson().fromJson(str, clazz);
    }

    public static <T> String toJsonAllowNull(T object) {
        return new GsonBuilder().serializeNulls().create().toJson(object);
    }

    public static <T> String toJson(T object) {
        return new GsonBuilder().create().toJson(object);
    }
}
