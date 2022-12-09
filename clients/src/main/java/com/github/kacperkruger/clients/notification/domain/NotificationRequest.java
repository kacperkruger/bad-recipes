package com.github.kacperkruger.clients.notification.domain;

public class NotificationRequest {

    private String receiver;

    private String subject;

    private String message;

    private String type;

    public NotificationRequest() {
    }

    public NotificationRequest(String receiver, String subject, String message, String type) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
