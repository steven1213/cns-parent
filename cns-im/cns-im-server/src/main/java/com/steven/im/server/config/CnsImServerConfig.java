package com.steven.im.server.config;

import com.steven.im.common.dispatcher.MessageDispatcher;
import com.steven.im.common.dispatcher.MessageHandlerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author steven.cao
 */
@Configuration
public class CnsImServerConfig {

    @Bean
    public MessageDispatcher messageDispatcher() {
        return new MessageDispatcher();
    }

    @Bean
    public MessageHandlerContainer messageHandlerContainer() {
        return new MessageHandlerContainer();
    }
}
