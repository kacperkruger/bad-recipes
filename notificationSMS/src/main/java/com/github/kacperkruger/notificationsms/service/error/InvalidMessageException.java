package com.github.kacperkruger.notificationsms.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Message must not be empty")
public class InvalidMessageException extends Exception {

    @Override
    public String getMessage() {
        return "Message must not be empty";
    }
}
