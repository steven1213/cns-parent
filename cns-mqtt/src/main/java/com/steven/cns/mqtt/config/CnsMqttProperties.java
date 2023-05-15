package com.steven.cns.mqtt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author steven.cao
 */
@Data
@Component
@ConfigurationProperties(prefix = CnsMqttProperties.PREFIX)
public class CnsMqttProperties {

    public static final String PREFIX = "cns.mqtt";

    private String broker;

    private String clientId;

    private String username;

    private String password;

    private String topic;


}
