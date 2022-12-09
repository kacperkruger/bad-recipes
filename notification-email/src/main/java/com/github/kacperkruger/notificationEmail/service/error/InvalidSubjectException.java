package com.github.kacperkruger.notificationEmail.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Subject can not be empty")
public class InvalidSubjectException extends EmailException {

    @Override
    public String getMessage() {
        return "Subject can not be empty";
    }
}
