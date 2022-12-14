package com.github.kacperkruger.clients.notification.service;

import com.github.kacperkruger.clients.notification.domain.NotificationSenderStrategy;
import com.github.kacperkruger.clients.notification.domain.NotificationType;
import com.github.kacperkruger.clients.notification.error.UnknownNotificationRequestType;

public interface NotificationSenderStrategyResolver {

    NotificationSenderStrategy getSender(NotificationType notificationType) throws UnknownNotificationRequestType;
}
