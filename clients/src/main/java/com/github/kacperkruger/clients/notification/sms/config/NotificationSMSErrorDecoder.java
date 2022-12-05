package com.github.kacperkruger.clients.notification.sms.config;

import com.github.kacperkruger.clients.notification.sms.error.InvalidSMSRequestException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class NotificationSMSErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseStatus.is4xxClientError()) {
            return new InvalidSMSRequestException();
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }
}
