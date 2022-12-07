package com.github.kacperkruger.clients.notification.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnknownNotificationRequestType extends ResponseStatusException {
    public UnknownNotificationRequestType() {
        super(HttpStatus.BAD_REQUEST, "Unknown notification type");
    }
}
