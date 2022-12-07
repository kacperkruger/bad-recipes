package com.github.kacperkruger.clients.notification.service;

import com.github.kacperkruger.clients.notification.domain.NotificationSenderStrategy;
import com.github.kacperkruger.clients.notification.domain.NotificationType;
import com.github.kacperkruger.clients.notification.error.UnknownNotificationRequestType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationSenderResolver implements NotificationSenderStrategyResolver {

    private final List<NotificationSenderStrategy> notificationSenderStrategies;

    public NotificationSenderResolver(List<NotificationSenderStrategy> notificationSenderStrategies) {
        this.notificationSenderStrategies = notificationSenderStrategies;
    }

    @Override
    public NotificationSenderStrategy getSender(NotificationType notificationType) {
        System.out.println(notificationSenderStrategies);
        return notificationSenderStrategies.stream()
                .filter(sender -> sender.getNotificationType() == notificationType)
                .findFirst()
                .orElseThrow(UnknownNotificationRequestType::new);
    }
}
