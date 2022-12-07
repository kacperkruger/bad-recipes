package com.github.kacperkruger.clients.notification.email;

import com.github.kacperkruger.clients.error.ExceptionMessage;
import com.github.kacperkruger.clients.notification.domain.NotificationRequest;
import com.github.kacperkruger.clients.notification.domain.NotificationSenderStrategy;
import com.github.kacperkruger.clients.notification.domain.NotificationType;
import com.github.kacperkruger.clients.notification.email.domain.EmailRequest;
import com.github.kacperkruger.clients.notification.email.error.InvalidEmailRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.github.kacperkruger.clients.notification.domain.NotificationType.EMAIL;

@Service
public class NotificationEmailSender implements NotificationSenderStrategy {

    private static final String PATH = "/api/v1/notification-email";

    private final WebClient webClient;

    @Value("${clients.notification-email.url}")
    private String NOTIFICATION_EMAIL_URL;

    public NotificationEmailSender(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public void sendNotification(NotificationRequest notificationRequest) {
        EmailRequest emailRequest = new EmailRequest(
                notificationRequest.getReceiver(),
                notificationRequest.getSubject(),
                notificationRequest.getMessage()
        );

        webClient
                .post()
                .uri(NOTIFICATION_EMAIL_URL + PATH).body(Mono.just(emailRequest), EmailRequest.class)
                .retrieve()
                .onStatus(
                        HttpStatus.BAD_REQUEST::equals,
                        response -> response.bodyToMono(ExceptionMessage.class)
                                .map(exception -> new InvalidEmailRequestException(HttpStatus.resolve(exception.getStatus()), exception.getMessage()))
                )
                .bodyToMono(Void.class)
                .block();
    }

    @Override
    public NotificationType getNotificationType() {
        return EMAIL;
    }
}
