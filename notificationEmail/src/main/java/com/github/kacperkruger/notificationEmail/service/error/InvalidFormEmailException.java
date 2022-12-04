package com.github.kacperkruger.notificationEmail.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid format of source email address")
public class InvalidFormEmailException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid from email address";
    }
}
