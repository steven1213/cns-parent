package com.steven.cns.infra.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author steven.cao
 */
public final class GsonUtils {

    private GsonUtils() {
    }

    /**
     * 将json字符串转换为对象
     *
     * @param str   json字符串
     * @param clazz 对象类型
     * @param <T>   对象类型
     * @return 对象
     */
    public static <T> T fromJson(String str, Class<T> clazz) {
        return new Gson().fromJson(str, clazz);
    }

    /**
     * 将对象转换为json字符串
     *
     * @param object 对象类型
     * @param <T>    对象类型
     * @return 对象
     */
    public static <T> String toJsonAllowNull(T object) {
        return new GsonBuilder().serializeNulls().create().toJson(object);
    }

    /**
     * 将json字符串转换为对象
     *
     * @param object 对象类型
     * @param <T>    对象类型
     * @return 对象
     */
    public static <T> String prettyPrint(T object) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(object);
    }

    /**
     * 将对象转换为json字符串
     *
     * @param object 对象
     * @param <T>    对象类型
     * @return json字符串
     */
    public static <T> String toJson(T object) {
        return new GsonBuilder().create().toJson(object);
    }
}
