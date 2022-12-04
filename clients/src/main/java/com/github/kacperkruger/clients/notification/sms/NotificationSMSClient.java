package com.github.kacperkruger.clients.notification.sms;

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
    void sendSMS(@RequestBody SMSRequest smsRequest);
}
