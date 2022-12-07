package com.github.kacperkruger.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "com.github.kacperkruger.clients",
                "com.github.kacperkruger.notification"
        })
@EnableFeignClients(
        basePackages = "com.github.kacperkruger.clients"
)
@PropertySources({
        @PropertySource("classpath:clients-default.properties")
})
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
}
