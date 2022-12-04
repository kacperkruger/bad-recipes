package com.github.kacperkruger.notificationSMS.controller;

import com.github.kacperkruger.clients.notification.NotificationResponse;
import com.github.kacperkruger.clients.notification.sms.SMSRequest;
import com.github.kacperkruger.notificationSMS.service.NotificationSMSService;
import com.github.kacperkruger.notificationSMS.service.error.InvalidMessageException;
import com.github.kacperkruger.notificationSMS.service.error.InvalidPhoneNumberException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification-sms")
public class NotificationSMSController {

    private final NotificationSMSService smsService;

    public NotificationSMSController(NotificationSMSService smsService) {
        this.smsService = smsService;
    }

    @PostMapping
    public void sendSMS(@RequestBody SMSRequest smsRequest) throws InvalidPhoneNumberException, InvalidMessageException, InterruptedException {
        smsService.validateAndSend(smsRequest);
    }
}
