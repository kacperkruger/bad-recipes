package com.github.kacperkruger.clients.notification.domain;

public class NotificationRequest {

    private String receiver;

    private String subject;

    private String message;

    private NotificationType type;

    public NotificationRequest() {
    }

    public NotificationRequest(String receiver, String subject, String message, NotificationType type) {
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
        this.type = type;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
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

    public NotificationType getType() { 
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
}
