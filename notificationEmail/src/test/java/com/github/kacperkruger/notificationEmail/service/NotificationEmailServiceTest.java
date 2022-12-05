package com.github.kacperkruger.notificationEmail.service;

import com.github.kacperkruger.clients.notification.email.domain.EmailRequest;
import com.github.kacperkruger.notificationEmail.service.error.*;
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

    SimpleMailMessage mailMessageTemplate;

    @BeforeEach
    void setUp() {
        MailSender mockMailSender = mock(MailSender.class);
        SimpleMailMessage mockMailMessageTemplate = mock(SimpleMailMessage.class);
        String fromEmailAddress = "test@gmail.com";
        mockMailMessageTemplate.setFrom(fromEmailAddress);
        mailMessageTemplate = mockMailMessageTemplate;
        mailSender = mockMailSender;
        emailService = new NotificationEmailService(mockMailSender, mockMailMessageTemplate);
    }

    @Test
    void validateAndSendShouldCallMailSenderMethodSendWhenEmailRequestCorrect() throws EmailException {
        EmailRequest correctEmailRequest = new EmailRequest("test@gmail.com", "test", "test");
        SimpleMailMessage correctMailMessage = new SimpleMailMessage();
        mailMessageTemplate.setTo(correctEmailRequest.getToEmail());
        mailMessageTemplate.setSubject(correctEmailRequest.getSubject());
        mailMessageTemplate.setText(correctEmailRequest.getMessage());

        emailService.validateAndSend(correctEmailRequest);

        verify(mailSender, times(1)).send(correctMailMessage);
    }

    @Test
    void validateEmailRequestShouldPassWhenEmailRequestCorrect() throws EmailException {
        EmailRequest correctEmailRequest = new EmailRequest("test@gmail.com", "test", "test");
        emailService.validateEmailRequest(correctEmailRequest);
    }

    @Test
    void validateEmailRequestShouldThrowInvalidToEmailExceptionWhenToEmailNotValidate() {
        EmailRequest invalidEmailRequest = new EmailRequest("test", "test", "test");

        Exception exception = assertThrows(InvalidToEmailException.class, () -> emailService.validateEmailRequest(invalidEmailRequest));

        String expectedMessage = "Invalid to email address";
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void validateEmailRequestShouldThrowInvalidSubjectExceptionWhenSubjectIsEmpty() {
        EmailRequest invalidEmailRequest = new EmailRequest("test@gmail.com", "", "test");

        Exception exception = assertThrows(InvalidSubjectException.class, () -> emailService.validateEmailRequest(invalidEmailRequest));

        String expectedMessage = "Subject can not be empty";
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void validateEmailRequestShouldThrowInvalidMessageExceptionWhenMessageIsEmpty() {
        EmailRequest invalidEmailRequest = new EmailRequest("test@gmail.com", "test", "");

        Exception exception = assertThrows(InvalidMessageException.class, () -> emailService.validateEmailRequest(invalidEmailRequest));

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