package com.github.kacperkruger.notificationsms.service;

import com.github.kacperkruger.clients.notification.sms.domain.SMSRequest;
import com.github.kacperkruger.notificationSMS.sender.SMSSender;
import com.github.kacperkruger.notificationSMS.service.NotificationSMSService;
import com.github.kacperkruger.notificationSMS.service.error.InvalidMessageException;
import com.github.kacperkruger.notificationSMS.service.error.InvalidPhoneNumberException;
import com.github.kacperkruger.notificationSMS.service.error.SMSRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationSMSServiceTest {

    @MockBean
    private SMSSender smsSender;

    private NotificationSMSService smsService;

    @BeforeEach
    void setUp() {
        SMSSender smsSender = mock(SMSSender.class);
        this.smsSender = smsSender;
        this.smsService = new NotificationSMSService(smsSender);
    }

    @Test
    void validateAndSendShouldCallSMSSenderSendMethodWhenPhoneNumberAndMessageCorrect() throws InterruptedException, SMSRequestException {
        SMSRequest correctSMSRequest = new SMSRequest("+48123456789", "Hello World!");
        smsService.validateAndSend(correctSMSRequest);
        verify(smsSender, times(1)).send(correctSMSRequest);
    }

    @Test
    void validateSMSRequestShouldThrowInvalidPhoneNumberExceptionWhenPhoneNumberInvalid() {
        SMSRequest invalidSMSRequest = new SMSRequest("123456789", "hello");

        Exception exception = assertThrows(InvalidPhoneNumberException.class, () -> smsService.validateSMSRequest(invalidSMSRequest));

        String expectedMessage = "Invalid phone number";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void validateSMSRequestShouldThrowInvalidMessageExceptionWhenPhoneNumberCorrectAndMessageInvalid() {
        SMSRequest invalidSMSRequest = new SMSRequest("+48123456789", "");

        Exception exception = assertThrows(InvalidMessageException.class, () -> smsService.validateSMSRequest(invalidSMSRequest));

        String expectedMessage = "Message must not be empty";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void validateSMSRequestShouldPassWhenPhoneNumberAndMessageCorrect() throws SMSRequestException {
        SMSRequest correctSMSRequest = new SMSRequest("+48123456789", "Hello World!");
        smsService.validateSMSRequest(correctSMSRequest);
    }

    @Test
    void isPhoneNumberCorrectShouldReturnTrueWhenPhoneNumberCorrect() {
        String correctPhoneNumber = "+48123456789";
        assertTrue(smsService.isPhoneNumberCorrect(correctPhoneNumber));
    }

    @Test
    void isPhoneNumberCorrectShouldReturnFalseWhenPhoneNumberIsEmpty() {
        assertFalse(smsService.isPhoneNumberCorrect(""));
    }

    @Test
    void isPhoneNumberCorrectShouldReturnFalseWhenPhoneNumberDoesNotContainsDiallingCode() {
        String phoneNumberWithoutDiallingCode = "123456789";
        assertFalse(smsService.isPhoneNumberCorrect(phoneNumberWithoutDiallingCode));
    }

    @Test
    void isPhoneNumberCorrectShouldReturnFalseWhenPhoneNumberInvalidLength() {
        String tooShortPhoneNumber = "+4812345678";
        String tooLongPhoneNumber = "+481234567890";

        assertFalse(smsService.isPhoneNumberCorrect(tooShortPhoneNumber));
        assertFalse(smsService.isPhoneNumberCorrect(tooLongPhoneNumber));
    }

    @Test
    void isMessageNotEmptyShouldReturnTrueWhenMessageCorrect() {
        String correctMessage = "Hello World!";
        assertTrue(smsService.isMessageNotEmpty(correctMessage));
    }

    @Test
    void isMessageNotEmptyShouldReturnFalseWhenMessageIsBlank() {
        String blankMessage = "";
        assertFalse(smsService.isMessageNotEmpty(blankMessage));
    }
}