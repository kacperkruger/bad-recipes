package com.github.kacperkruger.notification.controller;

import com.github.kacperkruger.clients.notification.domain.NotificationRequest;
import com.github.kacperkruger.clients.notification.sms.error.InvalidSMSRequestException;
import com.github.kacperkruger.notification.service.NotificationService;
import feign.FeignException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) throws Throwable {
        notificationService.sendNotification(notificationRequest);
    }
}
