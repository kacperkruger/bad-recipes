package com.github.kacperkruger.notificationSMS.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Message must not be empty")
public class InvalidMessageException extends SMSRequestException {

    @Override
    public String getMessage() {
        return "Message must not be empty";
    }
}
