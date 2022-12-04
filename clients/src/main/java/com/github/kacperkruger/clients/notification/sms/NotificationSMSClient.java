package com.github.kacperkruger.clients.notification.sms;

import com.github.kacperkruger.clients.notification.NotificationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notification-sms",
        url = "${clients.notification-sms.url}",
        path = "api/v1/notification-sms"
)
public interface NotificationSMSClient {

    @PostMapping
    NotificationResponse sendSMS(@RequestBody SMSRequest smsRequest);
}
