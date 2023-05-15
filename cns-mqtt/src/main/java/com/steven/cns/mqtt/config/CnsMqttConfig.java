package com.steven.cns.mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.event.MqttConnectionFailedEvent;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;

import javax.net.ssl.SSLSocketFactory;

/**
 * @author steven.cao
 */
@Configuration
@EnableIntegration
@IntegrationComponentScan
public class CnsMqttConfig {

//    @Autowired
    private CnsMqttProperties cnsMqttProperties;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
//        factory.setSocketFactory(sslSocketFactory);
        return factory;
    }

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{cnsMqttProperties.getBroker()});
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(10);
        options.setUserName(cnsMqttProperties.getUsername());
        options.setPassword(cnsMqttProperties.getPassword().toCharArray());
        options.setCleanSession(true);
        options.setKeepAliveInterval(60);
        return options;
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = null;
//        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(cnsMqttProperties.getClientId(), mqttConnectOptions(), cnsMqttProperties.getTopic());
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setApplicationEventPublisher(event -> {
            if (event instanceof MqttConnectionFailedEvent) {
                // handle connection failure
                System.out.println("Connection failed, retrying...");
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(5000);
//                        adapter.connect();
                        System.out.println("Reconnected");
                        break;
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
//                    } catch (MqttException e) {
//                        System.out.println("Reconnection attempt " + (i + 1) + " failed");
                    }
                }
            }
        });
        return adapter;
    }

//    @Bean
//    @ServiceActivator(inputChannel = "mqttOutboundChannel")
//    public MessageHandler outbound() {
//        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(cnsMqttProperties.getClientId(), mqttConnectOptions());
//        messageHandler.setAsync(true);
//        messageHandler.setDefaultTopic(cnsMqttProperties.getTopic());
//        return messageHandler;
//    }

    @ServiceActivator(inputChannel = "mqttInboundChannel")
    public void handleIncomingMessage(Message<?> message) throws MessagingException {
        System.out.println("Received message: " + message.getPayload());
    }

    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    public interface MqttGateway {
        void sendToMqtt(String data);
    }
}
