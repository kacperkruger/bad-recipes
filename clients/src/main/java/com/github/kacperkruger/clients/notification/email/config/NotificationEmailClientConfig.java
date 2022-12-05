package com.github.kacperkruger.clients.notification.email.config;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class NotificationEmailClientConfig {

    @Bean
    public ErrorDecoder emailRequestErrorDecoder() {
        return new NotificationEmailErrorDecoder();
    }
}
