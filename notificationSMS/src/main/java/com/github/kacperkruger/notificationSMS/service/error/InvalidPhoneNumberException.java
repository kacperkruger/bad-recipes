package com.github.kacperkruger.notificationSMS.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid phone number")
public class InvalidPhoneNumberException extends SMSRequestException {

    @Override
    public String getMessage() {
        return "Invalid phone number";
    }
}
