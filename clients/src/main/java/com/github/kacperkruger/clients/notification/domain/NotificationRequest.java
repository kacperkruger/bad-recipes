package com.github.kacperkruger.clients.notification.domain;

public class NotificationRequest {

    private String receiver;

    private String subject;

    private String message;

    private NotificationType notificationType;

    public NotificationRequest() {
    }

    public NotificationRequest(String to, String subject, String message, NotificationType notificationType) {
        this.receiver = to;
        this.subject = subject;
        this.message = message;
        this.notificationType = notificationType;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String to) {
        this.receiver = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }
}
