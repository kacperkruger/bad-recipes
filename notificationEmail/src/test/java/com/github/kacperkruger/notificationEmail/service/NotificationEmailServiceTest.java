package com.github.kacperkruger.notificationEmail.service;

import com.github.kacperkruger.notificationEmail.domain.EmailRequest;
import com.github.kacperkruger.clients.notification.email.error.InvalidFormEmailException;
import com.github.kacperkruger.clients.notification.email.error.InvalidMessageException;
import com.github.kacperkruger.clients.notification.email.error.InvalidSubjectException;
import com.github.kacperkruger.clients.notification.email.error.InvalidToEmailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class NotificationEmailServiceTest {

    @MockBean
    MailSender mailSender;

    NotificationEmailService emailService;

    @BeforeEach
    void setUp() {
        MailSender mockMailSender = mock(MailSender.class);
        mailSender = mockMailSender;
        emailService = new NotificationEmailService(mockMailSender);
    }

    @Test
    void validateAndSendShouldCallMailSenderMethodSendWhenEmailRequestCorrect() throws InvalidSubjectException, InvalidMessageException, InvalidFormEmailException, InvalidToEmailException {
        EmailRequest correctEmailRequest = new EmailRequest("test@gmail.com", "test@gmail.com", "test", "test");
        SimpleMailMessage correctMailMessage = new SimpleMailMessage();
        correctMailMessage.setFrom(correctEmailRequest.getFromEmail());
        correctMailMessage.setTo(correctEmailRequest.getToEmail());
        correctMailMessage.setSubject(correctEmailRequest.getSubject());
        correctMailMessage.setText(correctEmailRequest.getMessage());

        emailService.validateAndSend(correctEmailRequest);

        verify(mailSender, times(1)).send(correctMailMessage);
    }

    @Test
    void validateEmailRequestShouldPassWhenEmailRequestCorrect() throws InvalidSubjectException, InvalidMessageException, InvalidFormEmailException, InvalidToEmailException {
        EmailRequest correctEmailRequest = new EmailRequest("test@gmail.com", "test@gmail.com", "test", "test");
        emailService.validateEmailRequest(correctEmailRequest);
    }

    @Test
    void validateEmailRequestShouldThrowInvalidFormEmailExceptionWhenFromEmailInvalid() {
        EmailRequest invalidEmailRequest = new EmailRequest("test", "test@gmail.com", "test", "test");

        Exception exception = assertThrows(InvalidFormEmailException.class, () -> {
            emailService.validateEmailRequest(invalidEmailRequest);
        });

        String expectedMessage = "Invalid from email address";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void validateEmailRequestShouldThrowInvalidToEmailExceptionWhenToEmailNotValidate() {
        EmailRequest invalidEmailRequest = new EmailRequest("test@gmail.com", "test", "test", "test");

        Exception exception = assertThrows(InvalidToEmailException.class, () -> {
            emailService.validateEmailRequest(invalidEmailRequest);
        });

        String expectedMessage = "Invalid to email address";
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void validateEmailRequestShouldThrowInvalidSubjectExceptionWhenSubjectIsEmpty() {
        EmailRequest invalidEmailRequest = new EmailRequest("test@gmail.com", "test@gmail.com", "", "test");

        Exception exception = assertThrows(InvalidSubjectException.class, () -> {
            emailService.validateEmailRequest(invalidEmailRequest);
        });

        String expectedMessage = "Subject can not be empty";
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void validateEmailRequestShouldThrowInvalidMessageExceptionWhenMessageIsEmpty() {
        EmailRequest invalidEmailRequest = new EmailRequest("test@gmail.com", "test@gmail.com", "test", "");

        Exception exception = assertThrows(InvalidMessageException.class, () -> {
            emailService.validateEmailRequest(invalidEmailRequest);
        });

        String expectedMessage = "Message can not be empty";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void isNotCorrectEmailAddressShouldReturnTrueForEmailWithoutAtSign() {
        String invalidEmailAddress = "test.gmail.com";

        assertTrue(emailService.isNotCorrectEmailAddress(invalidEmailAddress));
    }

    @Test
    void isNotCorrectEmailAddressShouldReturnTrueForEmailWithoutLocalPart() {
        String invalidEmailAddress = "@gmail.com";

        assertTrue(emailService.isNotCorrectEmailAddress(invalidEmailAddress));
    }

    @Test
    void isNotCorrectEmailAddressShouldReturnTrueForEmailWithoutDomainPart(){
        String invalidEmailAddress = "test@";

        assertTrue(emailService.isNotCorrectEmailAddress(invalidEmailAddress));
    }

    @Test
    void isNotCorrectEmailAddressShouldReturnTrueForEmailWithoutTLD() {
        String invalidEmailAddress = "test@gmail";

        assertTrue(emailService.isNotCorrectEmailAddress(invalidEmailAddress));
    }

    @Test
    void isNotCorrectEmailAddressShouldReturnFalseForCorrectEmail() {
        String correctEmail = "test@gmail.com";

        assertFalse(emailService.isNotCorrectEmailAddress(correctEmail));
    }
}