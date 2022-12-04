package com.github.kacperkruger.notificationEmail.controller;

import com.github.kacperkruger.notificationEmail.domain.EmailRequest;
import com.github.kacperkruger.notificationEmail.service.NotificationEmailService;
import org.springframework.lang.NonNull;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMailMessage;
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
    public void sendMessage(@RequestBody EmailRequest emailRequest) {
        notificationEmailService.validateAndSend(emailRequest);
    }
}
