package com.github.kacperkruger.clients.notification.email.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid format of target email address")
public class InvalidToEmailException extends EmailException {

    @Override
    public String getMessage() {
        return "Invalid to email address";
    }
}
