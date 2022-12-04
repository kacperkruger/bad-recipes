package com.github.kacperkruger.clients.notification.email;

import com.github.kacperkruger.clients.notification.NotificationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notification-email",
        url = "${clients.notification-email.url}",
        path = "api/v1/notification-email"

)
public interface NotificationEmailClient {

    @PostMapping
    NotificationResponse sendMessage(@RequestBody EmailRequest emailRequest);
}
