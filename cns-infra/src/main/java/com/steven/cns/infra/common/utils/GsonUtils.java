package com.steven.cns.infra.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author steven.cao
 */
public final class GsonUtils {
    /**
     * 线程安全的
     */
    private static final Gson GSON;

    /**
     * 不过滤空值
     */
    private static final Gson GSON_NULL;

    static {
        GSON = new GsonBuilder()
                //当Map的key为复杂对象时,需要开启该方法
                .enableComplexMapKeySerialization()
                // 当字段值为空或null时，依然对该字段进行转换
                .serializeNulls()
                //打开Export注解，但打开了这个注解,副作用，要转换和不转换都要加注解
//                .excludeFieldsWithoutExposeAnnotation()
                //序列化日期格式  "yyyy-MM-dd"
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                //自动格式化换行
                .setPrettyPrinting()
                //防止特殊字符出现乱码
                .disableHtmlEscaping()
                .create();
        GSON_NULL = new GsonBuilder()
                //当Map的key为复杂对象时,需要开启该方法
                .enableComplexMapKeySerialization()
                //当字段值为空或null时，依然对该字段进行转换
                .serializeNulls()
                //打开Export注解，但打开了这个注解,副作用，要转换和不转换都要加注解
//                .excludeFieldsWithoutExposeAnnotation()
                //序列化日期格式  "yyyy-MM-dd HH:mm:ss"
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                //自动格式化换行
                .setPrettyPrinting()
                //防止特殊字符出现乱码
                .disableHtmlEscaping()
                .create();
    }

    private GsonUtils() {
    }

    /**
     * 获取gson解析器
     */
    public static Gson getGson() {
        return GSON;
    }

    /**
     * 获取gson解析器 有空值 解析
     */
    public static Gson getWriteNullGson() {
        return GSON_NULL;
    }


    /**
     * 根据对象返回json  过滤空值字段
     */
    public static String toJsonStringIgnoreNull(Object object) {
        return GSON.toJson(object);
    }

    /**
     * 根据对象返回json  不过滤空值字段
     */
    public static String toJsonString(Object object) {
        return GSON_NULL.toJson(object);
    }


    /**
     * 将字符串转化对象
     *
     * @param json     源字符串
     * @param classOfT 目标对象类型
     * @param <T>
     * @return
     */
    public static <T> T strToJavaBean(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }

    /**
     * 将json转化为对应的实体对象
     * new TypeToken<List<T>>() {}.getType()
     * new TypeToken<Map<String, T>>() {}.getType()
     * new TypeToken<List<Map<String, T>>>() {}.getType()
     */
    public static <T> T fromJson(String json, Type typeOfT) {
        return GSON.fromJson(json, typeOfT);
    }

    /**
     * 转成list
     *
     * @param gsonString -
     * @param cls        -
     * @return t
     */
    public static <T> List<T> strToList(String gsonString, Class<T> cls) {
        return GSON.fromJson(gsonString, new TypeToken<List<T>>() {
        }.getType());
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString -
     * @return -
     */
    public static <T> List<Map<String, T>> strToListMaps(String gsonString) {
        return GSON.fromJson(gsonString, new TypeToken<List<Map<String, String>>>() {
        }.getType());
    }
}
