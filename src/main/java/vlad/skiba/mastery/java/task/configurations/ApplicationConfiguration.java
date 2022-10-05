package vlad.skiba.mastery.java.task.configurations;

import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.context.annotation.Bean;

/**
 * Copyright Vlad Skiba (c) 2022.
 */
@EnableJms
@Configuration
public class ApplicationConfiguration implements JmsListenerConfigurer {

    @Bean
    public DefaultMessageHandlerMethodFactory handlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(messageConverter());
        return factory;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(handlerMethodFactory());
    }

}
