package com.github.kacperkruger.clients.notification.sms;

import com.github.kacperkruger.clients.error.ExceptionMessage;
import com.github.kacperkruger.clients.notification.domain.NotificationRequest;
import com.github.kacperkruger.clients.notification.domain.NotificationSenderStrategy;
import com.github.kacperkruger.clients.notification.domain.NotificationType;
import com.github.kacperkruger.clients.notification.email.domain.EmailRequest;
import com.github.kacperkruger.clients.notification.sms.domain.SMSRequest;
import com.github.kacperkruger.clients.notification.sms.error.InvalidSMSRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.github.kacperkruger.clients.notification.domain.NotificationType.SMS;

@Service
public class NotificationSMSSender implements NotificationSenderStrategy {

    private static final String PATH = "/api/v1/notification-email";

    private final WebClient webClient;

    @Value("${clients.notification-sms.url}")
    private String NOTIFICATION_SMS_URL;

    public NotificationSMSSender(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public void sendNotification(NotificationRequest notificationRequest) {
        SMSRequest smsRequest = new SMSRequest(notificationRequest.getReceiver(), notificationRequest.getMessage());

        webClient
                .post()
                .uri(NOTIFICATION_SMS_URL + PATH).body(Mono.just(smsRequest), EmailRequest.class)
                .retrieve()
                .onStatus(
                        HttpStatus.BAD_REQUEST::equals,
                        response -> response.bodyToMono(ExceptionMessage.class)
                                .map(exception -> new InvalidSMSRequestException(HttpStatus.resolve(exception.getStatus()), exception.getMessage()))
                )
                .bodyToMono(Void.class)
                .block();
    }

    @Override
    public NotificationType getNotificationType() {
        return SMS;
    }
}
