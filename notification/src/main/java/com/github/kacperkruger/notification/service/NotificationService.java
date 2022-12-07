package com.github.kacperkruger.notification.service;

import com.github.kacperkruger.clients.notification.domain.NotificationRequest;
import com.github.kacperkruger.clients.notification.service.NotificationSenderStrategyResolver;
import com.github.kacperkruger.notification.domain.Notification;
import com.github.kacperkruger.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.github.kacperkruger.clients.notification.domain.NotificationStatus.ERROR;
import static com.github.kacperkruger.clients.notification.domain.NotificationStatus.SENT;

@Service
public class NotificationService {

    private final NotificationSenderStrategyResolver notificationSenderResolver;

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationSenderStrategyResolver notificationSenderResolver, NotificationRepository notificationRepository) {
        this.notificationSenderResolver = notificationSenderResolver;
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification(NotificationRequest notificationRequest) {
        Notification notification = new Notification(
                notificationRequest.getReceiver(),
                notificationRequest.getSubject(),
                LocalDateTime.now(),
                notificationRequest.getType().name(),
                SENT.name()
        );

        try {
            notificationSenderResolver.getSender(notificationRequest.getType()).sendNotification(notificationRequest);
            notification.setStatus(SENT);
            notificationRepository.save(notification);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            notification.setStatus(ERROR);
            notificationRepository.save(notification);
            throw e;
        }
    }
}
