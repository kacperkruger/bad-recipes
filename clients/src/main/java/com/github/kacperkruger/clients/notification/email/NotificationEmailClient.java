package com.github.kacperkruger.clients.notification.email;

import com.github.kacperkruger.clients.notification.email.error.EmailException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notification-email",
        path = "api/v1/notification-email"

)
public interface NotificationEmailClient {

    @PostMapping
    void sendMessage(@RequestBody EmailRequest emailRequest) throws EmailException;
}
