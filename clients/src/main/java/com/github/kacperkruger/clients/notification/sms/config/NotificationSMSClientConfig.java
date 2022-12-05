package com.github.kacperkruger.clients.notification.sms.config;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class NotificationSMSClientConfig {

    @Bean
    public ErrorDecoder smsRequestErrorDecoder() {
        return new NotificationSMSErrorDecoder();
    }
}
