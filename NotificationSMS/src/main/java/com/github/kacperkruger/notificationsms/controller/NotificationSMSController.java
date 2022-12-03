package com.github.kacperkruger.notificationsms.controller;

import com.github.kacperkruger.notificationsms.domain.SMSRequest;
import com.github.kacperkruger.notificationsms.service.NotificationSMSService;
import com.github.kacperkruger.notificationsms.service.error.InvalidMessage;
import com.github.kacperkruger.notificationsms.service.error.InvalidPhoneNumber;
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
    public void sendSMS(@RequestBody SMSRequest smsRequest) throws InvalidPhoneNumber, InvalidMessage {
        smsService.validateAndSend(smsRequest);
    }
}
