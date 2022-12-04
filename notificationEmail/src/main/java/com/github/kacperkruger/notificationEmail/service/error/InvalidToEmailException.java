package com.github.kacperkruger.notificationEmail.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid format of target email address")
public class InvalidToEmailException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid to email address";
    }
}
