package com.github.kacperkruger.clients.notification.email.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Message can not be empty")
public class InvalidMessageException extends EmailException {

    @Override
    public String getMessage() {
        return "Message can not be empty";
    }
}
