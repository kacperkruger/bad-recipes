package com.github.kacperkruger.clients.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kacperkruger.clients.error.ExceptionMessage;
import feign.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class ExceptionMessageParser {

    public static Optional<ExceptionMessage> parse(Response response) {
        ExceptionMessage message;

        try (InputStream bodyIs = response.body()
                .asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, ExceptionMessage.class);
            return Optional.of(message);
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
