package com.github.kacperkruger.notificationEmail.service;

import com.github.kacperkruger.clients.notification.email.domain.EmailRequest;
import com.github.kacperkruger.notificationEmail.service.error.*;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


@Service
public class NotificationEmailService {

    private final MailSender mailSender;

    private final SimpleMailMessage mailMessageTemplate;

    public NotificationEmailService(MailSender mailSender, SimpleMailMessage mailMessageTemplate) {
        this.mailSender = mailSender;
        this.mailMessageTemplate = mailMessageTemplate;
    }

    public void validateAndSend(EmailRequest emailRequest) throws EmailException {
        validateEmailRequest(emailRequest);

        mailMessageTemplate.setTo(emailRequest.getToEmail());
        mailMessageTemplate.setSubject(emailRequest.getSubject());
        mailMessageTemplate.setText(emailRequest.getMessage());

        mailSender.send(mailMessageTemplate);
    }

    public void validateEmailRequest(EmailRequest emailRequest) throws EmailException {
        if (isNotCorrectEmailAddress(emailRequest.getToEmail())) throw new InvalidToEmailException();
        if (emailRequest.getSubject().isEmpty()) throw new InvalidSubjectException();
        if (emailRequest.getMessage().isEmpty()) throw new InvalidMessageException();
    }

    public boolean isNotCorrectEmailAddress(String emailAddress) {
        return !EmailValidator.getInstance().isValid(emailAddress);
    }
}
