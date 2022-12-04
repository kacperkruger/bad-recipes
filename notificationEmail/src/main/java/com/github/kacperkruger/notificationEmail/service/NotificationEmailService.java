package com.github.kacperkruger.notificationEmail.service;

import com.github.kacperkruger.notificationEmail.domain.EmailRequest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class NotificationEmailService {

    private final Pattern EMAIL_PATTERN = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$");

    private final MailSender mailSender;

    public NotificationEmailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void validateAndSend(EmailRequest emailRequest) {
        validateEmailRequest(emailRequest);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailRequest.getFromEmail());
        mailMessage.setTo(emailRequest.getToEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());

        mailSender.send(mailMessage);
    }

    public void validateEmailRequest(EmailRequest emailRequest) {
        if (isNotCorrectEmail(emailRequest.getFromEmail())) throw new IllegalStateException();
        if (isNotCorrectEmail(emailRequest.getToEmail())) throw new IllegalStateException();
        if (emailRequest.getSubject().isEmpty()) throw new IllegalStateException();
        if (emailRequest.getMessage().isEmpty()) throw new IllegalStateException();
    }

    public boolean isNotCorrectEmail(String email) {
        return !EMAIL_PATTERN.matcher(email).matches();
    }
}
