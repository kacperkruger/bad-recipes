package com.github.kacperkruger.notification.repository;

import com.github.kacperkruger.notification.domain.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Long, Notification> {
}
