package com.steven.cns.infra.common.okhttp;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * @author dr.panda
 */
public class OkHttpConfiguration {

    @Autowired
    private OkHttpConfig okHttpConfig;

    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectionPool(pool())
                .connectTimeout(okHttpConfig.getConnectTimeoutMs(), TimeUnit.MICROSECONDS)
                .readTimeout(okHttpConfig.getReadTimeoutMs(), TimeUnit.MICROSECONDS)
                .writeTimeout(okHttpConfig.getWriteTimeoutMs(), TimeUnit.MICROSECONDS)
                .build();
        OkHttpUtils.setOkHttpClient(client);
        return client;
    }

    public ConnectionPool pool() {
        return new ConnectionPool(okHttpConfig.getMaxIdle(), okHttpConfig.getKeepAliveDurationSec(), TimeUnit.SECONDS);
    }
}
