package com.steven.cns.infra.common.client.okhttp;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;


/**
 * @author steven.cao
 */
@Getter
@Setter
@Validated
@Component
@ConditionalOnProperty(name = "okhttp", havingValue = "true")
@ConfigurationProperties(prefix = "okhttp")
public class OkHttpConfig {

    @NotNull
    private Long connectTimeoutMs;
    @NotNull
    private Long readTimeoutMs;
    @NotNull
    private Long writeTimeoutMs;
    @NotNull
    private Integer maxIdle;
    @NotNull
    private Long keepAliveDurationSec;
}
