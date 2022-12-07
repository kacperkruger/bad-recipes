package com.github.kacperkruger.clients.notification.service;

import com.github.kacperkruger.clients.notification.domain.NotificationSenderStrategy;
import com.github.kacperkruger.clients.notification.domain.NotificationType;

public interface NotificationSenderStrategyResolver {

    NotificationSenderStrategy getSender(NotificationType notificationType);
}
