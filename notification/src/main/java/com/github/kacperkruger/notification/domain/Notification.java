package com.github.kacperkruger.notification.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue
    private Long id;

    private String to;

    private String subject;

    private LocalDateTime dateOfSend;

    private NotificationType notificationType;

    public Notification() {
    }

    public Notification(Long id, String to, String subject, LocalDateTime dateOfSend, NotificationType notificationType) {
        this.id = id;
        this.to = to;
        this.subject = subject;
        this.dateOfSend = dateOfSend;
        this.notificationType = notificationType;
    }

    public Notification(String to, String subject, LocalDateTime dateOfSend, NotificationType notificationType) {
        this.to = to;
        this.subject = subject;
        this.dateOfSend = dateOfSend;
        this.notificationType = notificationType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDateTime getDateOfSend() {
        return dateOfSend;
    }

    public void setDateOfSend(LocalDateTime dateOfSend) {
        this.dateOfSend = dateOfSend;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }
}
