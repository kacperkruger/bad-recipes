package com.github.kacperkruger.clients.notification.email.domain;

public class EmailRequest {

    private String fromEmail;

    private String toEmail;

    private String subject;

    private String message;

    public EmailRequest() {
    }

    public EmailRequest(String fromEmail, String toEmail, String subject, String message) {
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
        this.subject = subject;
        this.message = message;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail =fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
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
}
