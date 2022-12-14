package com.github.kacperkruger.notification.service;

import com.github.kacperkruger.clients.notification.domain.NotificationRequest;
import com.github.kacperkruger.clients.notification.domain.NotificationStatus;
import com.github.kacperkruger.clients.notification.domain.NotificationType;
import com.github.kacperkruger.clients.notification.error.UnknownNotificationRequestType;
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

    public void sendAndSaveNotification(NotificationRequest notificationRequest) {
        Notification notification = new Notification(notificationRequest, LocalDateTime.now());

        NotificationStatus status = sendNotification(notificationRequest);
        notification.setStatus(status);
        notificationRepository.save(notification);
    }

    private NotificationStatus sendNotification(NotificationRequest notificationRequest) {
        try {
            notificationSenderResolver
                    .getSender(parseNotificationType(notificationRequest.getType()))
                    .sendNotification(notificationRequest);
            return SENT;
        } catch (UnknownNotificationRequestType e) {
            return ERROR;
        }
    }

    private NotificationType parseNotificationType(String notificationType) {
        try {
            return NotificationType.valueOf(notificationType);
        } catch (IllegalArgumentException e) {
            return NotificationType.UNKNOWN;
        }
    }
}
