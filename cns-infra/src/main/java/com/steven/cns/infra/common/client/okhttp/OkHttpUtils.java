package com.steven.cns.infra.common.client.okhttp;

import com.steven.cns.infra.common.utils.GsonUtils;
import lombok.NonNull;
import okhttp3.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

/**
 * @author steven.cao
 */
public class OkHttpUtils {

    private static OkHttpClient okHttpClient;

    public static void setOkHttpClient(OkHttpClient okHttpClient) {
        OkHttpUtils.okHttpClient = okHttpClient;
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static <T> T get(@NonNull String url, Class<T> clazz) {
        return get(url, null, null, clazz);
    }

    public static void get(@NonNull String url, Callback callback) {
        get(url, null, null, callback);
    }

    public static <T> T get(@NonNull String url, Map<String, String> queryParameter, Class<T> clasz) {
        return get(url, null, queryParameter, clasz);
    }

    public static void get(@NonNull String url, Map<String, String> queryParameter, Callback callback) {
        get(url, null, queryParameter, callback);
    }

    public static <T> T get(@NonNull String url, Map<String, String> headerParameter, Map<String, String> queryParameter, Class<T> clasz) {
        Request request = processGetParameter(url, headerParameter, queryParameter);
        try (Response resp = okHttpClient.newCall(request).execute();) {
            return processResponse(resp, clasz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void get(@NonNull String url, Map<String, String> headerParameter, Map<String, String> queryParameter, Callback callback) {
        Request request = processGetParameter(url, headerParameter, queryParameter);
        okHttpClient.newCall(request).enqueue(callback);
    }

    private static Request processGetParameter(String url, Map<String, String> headerParameter, Map<String, String> queryParameter) {
        Request.Builder builder = new Request.Builder();
        if (!isEmptyMap(headerParameter)) {
            builder.headers(Headers.of(headerParameter));
        }
        if (isEmptyMap(queryParameter)) {
            builder.url(url);
        } else {
            boolean hasQuery;
            try {
                hasQuery = new URL(url).getQuery().isEmpty();
            } catch (MalformedURLException e) {
                throw new RuntimeException("url is illegal");
            }
            StringBuilder sb = new StringBuilder(url);
            if (!hasQuery) {
                sb.append("?1=1");
            }
            queryParameter.forEach((k, v) -> {
                sb.append("&").append(k).append("=").append(v);
            });
            builder.url(sb.toString());
        }
        return builder.build();
    }

    /**
     * POST Method With JSON begin---------------------------------
     */

    public static <T> T postJson(@NonNull String url, Class<T> clasz) {
        return postJson(url, null, null, clasz);
    }

    public static void postJson(@NonNull String url, Callback callback) {
        postJson(url, null, null, callback);
    }

    public static <T> T postJson(@NonNull String url, Map<String, String> headerParameter, Class<T> clasz) {
        return postJson(url, headerParameter, null, clasz);
    }

    public static void postJson(@NonNull String url, Map<String, String> headerParameter, Callback callback) {
        postJson(url, headerParameter, null, callback);
    }

    public static <T> T postJson(@NonNull String url, Map<String, String> headerParameter, Object bodyObj, Class<T> clasz) {
        Request request = processPostJsonParameter(url, headerParameter, bodyObj);
        try (Response resp = okHttpClient.newCall(request).execute();) {
            return processResponse(resp, clasz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void postJson(@NonNull String url, Map<String, String> headerParameter, Object bodyObj, Callback callback) {
        Request request = processPostJsonParameter(url, headerParameter, bodyObj);
        okHttpClient.newCall(request).enqueue(callback);
    }

    private static Request processPostJsonParameter(String url, Map<String, String> headerParameter, Object bodyObj) {
        Request.Builder builder = new Request.Builder().url(url);
        if (!Objects.isNull(bodyObj)) {
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), GsonUtils.toJson(bodyObj));
            builder.post(body);
        } else {
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "{}");
            builder.post(body);
        }
        if (!isEmptyMap(headerParameter)) {
            builder.headers(Headers.of(headerParameter));
        }
        return builder.build();
    }

    /**
     * POST Method With Form begin---------------------------------
     */

    public static <T> T postForm(@NonNull String url, Class<T> clasz) {
        return postForm(url, null, null, clasz);
    }

    public static void postForm(@NonNull String url, Callback callback) {
        postForm(url, null, null, callback);
    }

    public static <T> T postForm(@NonNull String url, Map<String, String> headerParameter, Class<T> clasz) {
        return postForm(url, headerParameter, null, clasz);
    }

    public static void postForm(@NonNull String url, Map<String, String> headerParameter, Callback callback) {
        postForm(url, headerParameter, null, callback);
    }

    public static <T> T postForm(@NonNull String url, Map<String, String> headerParameter, Map<String, String> parameters, Class<T> clasz) {
        Request request = processPostFormParameter(url, headerParameter, parameters);
        try (Response resp = okHttpClient.newCall(request).execute();) {
            return processResponse(resp, clasz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void postForm(@NonNull String url, Map<String, String> headerParameter, Map<String, String> parameters, Callback callback) {
        Request request = processPostFormParameter(url, headerParameter, parameters);
        okHttpClient.newCall(request).enqueue(callback);
    }

    private static Request processPostFormParameter(String url, Map<String, String> headerParameter, Map<String, String> parameters) {
        Request.Builder builder = new Request.Builder().url(url);
        if (!Objects.isNull(parameters)) {
            FormBody.Builder formBuilder = new FormBody.Builder();
            parameters.forEach(formBuilder::add);
            builder.post(formBuilder.build());
        }
        if (!isEmptyMap(headerParameter)) {
            builder.headers(Headers.of(headerParameter));
        }
        return builder.build();
    }

    @SuppressWarnings("unchecked")
    private static <T> T processResponse(Response resp, Class<T> clasz) throws IOException {
        if (resp.isSuccessful()) {
            if (Objects.equals(Void.class, clasz)) {
                return null;
            }
            String respStr = resp.body().string();
            if (Objects.equals(String.class, clasz)) {
                return (T) respStr;
            }
            return GsonUtils.fromJson(respStr, clasz);
        }
        return null;
    }

    private static boolean isEmptyMap(Map<? extends Object, ? extends Object> map) {
        return Objects.isNull(map) || map.isEmpty();
    }
}