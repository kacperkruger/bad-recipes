package com.github.kacperkruger.clients.notification.email.error;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class InvalidEmailRequestException extends ResponseStatusException {

    public InvalidEmailRequestException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public InvalidEmailRequestException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }
}
