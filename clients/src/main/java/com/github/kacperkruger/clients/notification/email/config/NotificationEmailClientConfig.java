package com.github.kacperkruger.clients.notification.email.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class NotificationEmailClientConfig {

    @Bean
    public WebClient getWebClient() {
        return WebClient.create();
    }
}
