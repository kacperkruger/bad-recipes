package com.github.kacperkruger.clients.notification.email.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid format of source email address")
public class InvalidFormEmailException extends EmailException {

    @Override
    public String getMessage() {
        return "Invalid from email address";
    }
}
