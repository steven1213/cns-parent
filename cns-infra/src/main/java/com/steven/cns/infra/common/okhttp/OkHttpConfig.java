package com.steven.cns.infra.common.okhttp;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


/**
 * @author steven.cao
 */
@Getter
@Setter
@Validated
@Component
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
