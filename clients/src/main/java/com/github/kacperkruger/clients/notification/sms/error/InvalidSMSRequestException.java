package com.github.kacperkruger.clients.notification.sms.error;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class InvalidSMSRequestException extends ResponseStatusException {

    public InvalidSMSRequestException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
