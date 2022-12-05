package com.github.kacperkruger.notification.service;

import com.github.kacperkruger.clients.notification.domain.NotificationRequest;
import com.github.kacperkruger.clients.notification.email.domain.EmailRequest;
import com.github.kacperkruger.clients.notification.email.NotificationEmailClient;
import com.github.kacperkruger.clients.notification.email.error.InvalidEmailRequestException;
import com.github.kacperkruger.clients.notification.sms.NotificationSMSClient;
import com.github.kacperkruger.clients.notification.sms.domain.SMSRequest;
import com.github.kacperkruger.clients.notification.sms.error.InvalidSMSRequestException;
import com.github.kacperkruger.notification.domain.Notification;
import com.github.kacperkruger.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.github.kacperkruger.clients.notification.domain.NotificationStatus.ERROR;
import static com.github.kacperkruger.clients.notification.domain.NotificationStatus.SENT;

@Service
public class NotificationService {

    private final NotificationSMSClient smsClient;

    private final NotificationEmailClient emailClient;

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationSMSClient smsClient, NotificationEmailClient emailClient, NotificationRepository notificationRepository) {
        this.smsClient = smsClient;
        this.emailClient = emailClient;
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification(NotificationRequest notificationRequest) {
        Notification parsedNotification = parseNotification(notificationRequest);

        switch (notificationRequest.getNotificationType()) {
            case SMS -> {
                System.out.println("sms");
                SMSRequest smsRequest = new SMSRequest(notificationRequest.getReceiver(), notificationRequest.getMessage());
                try {
                    smsClient.sendSMS(smsRequest);
                    parsedNotification.setStatus(SENT);
                    notificationRepository.save(parsedNotification);
                } catch (InvalidSMSRequestException e) {
                    parsedNotification.setStatus(ERROR);
                    notificationRepository.save(parsedNotification);
                    throw e;
                }
            }
            case EMAIL -> {
                System.out.println("email");
                EmailRequest emailRequest = new EmailRequest("no-reply@badrecipes.cf", notificationRequest.getReceiver(), notificationRequest.getSubject(), notificationRequest.getMessage());
                try {
                    emailClient.sendMessage(emailRequest);
                    parsedNotification.setStatus(SENT);
                    notificationRepository.save(parsedNotification);
                } catch (InvalidEmailRequestException e) {
                    parsedNotification.setStatus(ERROR);
                    notificationRepository.save(parsedNotification);
                    throw e;
                }
            }
            default -> System.out.println("other");
        }
    }

    private Notification parseNotification(NotificationRequest notificationRequest) {
        return new Notification(
                notificationRequest.getReceiver(),
                notificationRequest.getSubject(),
                LocalDateTime.now(),
                notificationRequest.getNotificationType()
        );
    }
}
