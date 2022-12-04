package com.github.kacperkruger.notification.service;

import com.github.kacperkruger.notification.domain.Notification;
import com.github.kacperkruger.notification.domain.NotificationRequest;
import com.github.kacperkruger.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification(NotificationRequest notificationRequest) {
        Notification parsedNotification = parseNotification(notificationRequest);
        notificationRepository.save(parsedNotification);
    }

    public Notification parseNotification(NotificationRequest notificationRequest) {
        return new Notification(
                notificationRequest.getTo(),
                notificationRequest.getSubject(),
                LocalDateTime.now(),
                notificationRequest.getNotificationType()
        );
    }
}
