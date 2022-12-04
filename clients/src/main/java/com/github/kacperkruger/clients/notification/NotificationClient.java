package com.github.kacperkruger.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "notification",
        url = "${clients.notification.url}",
        path = "api/v1/notification"
)
public interface NotificationClient {
}
