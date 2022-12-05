package com.github.kacperkruger.notificationEmail.controller;

import com.github.kacperkruger.clients.notification.email.EmailRequest;
import com.github.kacperkruger.clients.notification.email.error.EmailException;
import com.github.kacperkruger.notificationEmail.service.NotificationEmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification-email")
public class NotificationEmailController {

    private final NotificationEmailService notificationEmailService;

    public NotificationEmailController(NotificationEmailService notificationEmailService) {
        this.notificationEmailService = notificationEmailService;
    }

    @PostMapping
    public void sendMessage(@RequestBody EmailRequest emailRequest) throws EmailException {
        notificationEmailService.validateAndSend(emailRequest);
    }
}
