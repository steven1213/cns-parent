package com.steven.cns.auth2.auth.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author steven.cao
 */
@Configuration
public class CnsAccessTokenConfig {

    @Bean
    TokenStore tokenStore() {
//        return new CnsRedisTokenStore();
        return null;
    }
}
