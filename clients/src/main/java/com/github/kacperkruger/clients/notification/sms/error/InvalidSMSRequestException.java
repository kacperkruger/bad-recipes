package com.github.kacperkruger.clients.notification.sms.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid sms request")
public class InvalidSMSRequestException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid sms request";
    }
}
