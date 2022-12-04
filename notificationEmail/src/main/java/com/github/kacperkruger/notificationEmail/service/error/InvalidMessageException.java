package com.github.kacperkruger.notificationEmail.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Message can not be empty")
public class InvalidMessageException extends Exception {

    @Override
    public String getMessage() {
        return "Message can not be empty";
    }
}
