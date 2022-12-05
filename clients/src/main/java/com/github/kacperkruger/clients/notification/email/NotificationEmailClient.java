package com.github.kacperkruger.clients.notification.email;

import com.github.kacperkruger.clients.notification.email.config.NotificationEmailClientConfig;
import com.github.kacperkruger.clients.notification.email.domain.EmailRequest;
import com.github.kacperkruger.clients.notification.email.error.InvalidEmailRequestException;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notification-email",
        url = "${clients.notification-email.url}",
        path = "api/v1/notification-email",
        configuration = NotificationEmailClientConfig.class

)
public interface NotificationEmailClient {

    @PostMapping
    void sendMessage(@RequestBody EmailRequest emailRequest) throws InvalidEmailRequestException, FeignException;
}
