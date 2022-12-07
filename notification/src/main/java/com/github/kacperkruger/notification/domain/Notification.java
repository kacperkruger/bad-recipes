package com.github.kacperkruger.notification.domain;

import com.github.kacperkruger.clients.notification.domain.NotificationStatus;
import com.github.kacperkruger.clients.notification.domain.NotificationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String receiver;

    private String subject;

    private LocalDateTime dateOfSend;

    private String notificationType;

    private String status;

    public Notification() {
    }

    public Notification(Long id, String receiver, String subject, LocalDateTime dateOfSend, String notificationType, String status) {
        this.id = id;
        this.receiver = receiver;
        this.subject = subject;
        this.dateOfSend = dateOfSend;
        this.notificationType = notificationType;
        this.status = status;
    }

    public Notification(String receiver, String subject, LocalDateTime dateOfSend, String notificationType, String status) {
        this.receiver = receiver;
        this.subject = subject;
        this.dateOfSend = dateOfSend;
        this.notificationType = notificationType;
        this.status = status;
    }

    public Notification(String receiver, String subject, LocalDateTime dateOfSend, String notificationType) {
        this.receiver = receiver;
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

    public LocalDateTime getDateOfSend() {
        return dateOfSend;
    }

    public void setDateOfSend(LocalDateTime dateOfSend) {
        this.dateOfSend = dateOfSend;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType.name();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status.name();
    }
}
