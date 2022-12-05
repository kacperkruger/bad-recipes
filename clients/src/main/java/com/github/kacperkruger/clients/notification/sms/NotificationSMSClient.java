package com.github.kacperkruger.clients.notification.sms;

import com.github.kacperkruger.clients.notification.sms.config.NotificationSMSClientConfig;
import com.github.kacperkruger.clients.notification.sms.domain.SMSRequest;
import com.github.kacperkruger.clients.notification.sms.error.InvalidSMSRequestException;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notification-sms",
        url = "${clients.notification-sms.url}",
        path = "api/v1/notification-sms",
        configuration = NotificationSMSClientConfig.class
)
public interface NotificationSMSClient {

    @PostMapping
    void sendSMS(@RequestBody SMSRequest smsRequest) throws InvalidSMSRequestException, FeignException;
}
