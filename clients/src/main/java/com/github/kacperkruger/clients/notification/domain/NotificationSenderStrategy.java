package com.github.kacperkruger.clients.notification.domain;

public interface NotificationSenderStrategy {

    void sendNotification(NotificationRequest notificationRequest);

    NotificationType getNotificationType();
}
