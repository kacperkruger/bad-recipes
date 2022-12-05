package com.github.kacperkruger.clients.notification.sms.config;

import com.github.kacperkruger.clients.error.ExceptionMessage;
import com.github.kacperkruger.clients.notification.sms.error.InvalidSMSRequestException;
import com.github.kacperkruger.clients.utils.ExceptionMessageParser;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class NotificationSMSErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());
        Optional<ExceptionMessage> message = ExceptionMessageParser.parse(response);

        if (responseStatus.is4xxClientError() && message.isPresent()) {
            return new InvalidSMSRequestException(responseStatus, message.get().getMessage());
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }
}
