package com.github.kacperkruger.clients.notification.sms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kacperkruger.clients.error.ExceptionMessage;
import com.github.kacperkruger.clients.notification.sms.error.InvalidSMSRequestException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

public class NotificationSMSErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessage message;
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        try (InputStream bodyIs = response.body()
                .asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, ExceptionMessage.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }

        if (responseStatus.is4xxClientError()) {
            return new InvalidSMSRequestException(responseStatus, message.getMessage());
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }
}
