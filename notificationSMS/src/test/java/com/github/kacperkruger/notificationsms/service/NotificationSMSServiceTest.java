package com.github.kacperkruger.notificationsms.service;

import com.github.kacperkruger.notificationSMS.domain.SMSRequest;
import com.github.kacperkruger.notificationSMS.sender.SMSSender;
import com.github.kacperkruger.notificationSMS.service.NotificationSMSService;
import com.github.kacperkruger.notificationSMS.service.error.InvalidMessageException;
import com.github.kacperkruger.notificationSMS.service.error.InvalidPhoneNumberException;
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
    void validateAndSendShouldThrowInvalidPhoneNumberExceptionWhenPhoneNumberInvalid() {
        SMSRequest invalidSMSRequest = new SMSRequest("123456789", "hello");

        Exception exception = assertThrows(InvalidPhoneNumberException.class, () -> {
            smsService.validateAndSend(invalidSMSRequest);
        });

        String expectedMessage = "Invalid phone number";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void validateAndSendShouldThrowInvalidMessageExceptionWhenPhoneNumberCorrectAndMessageInvalid() {
        SMSRequest invalidSMSRequest = new SMSRequest("+48123456789", "");

        Exception exception = assertThrows(InvalidMessageException.class, () -> {
            smsService.validateAndSend(invalidSMSRequest);
        });

        String expectedMessage = "Message must not be empty";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void validatePhoneNumberShouldCallSMSSenderSendMethodWhenPhoneNumberAndMessageCorrect() throws InvalidMessageException, InvalidPhoneNumberException {
        SMSRequest correctSMSRequest = new SMSRequest("+48123456789", "Hello World!");

        smsService.validateAndSend(correctSMSRequest);

        verify(smsSender, times(1)).send(correctSMSRequest);
    }

    @Test
    void validatePhoneNumberShouldReturnTrueWhenPhoneNumberCorrect() {
        String correctPhoneNumber = "+48123456789";
        assertTrue(smsService.validatePhoneNumber(correctPhoneNumber));
    }

    @Test
    void validatePhoneNumberShouldReturnFalseWhenPhoneNumberIsEmpty() {
        assertFalse(smsService.validatePhoneNumber(""));
    }

    @Test
    void validatePhoneNumberShouldReturnFalseWhenPhoneNumberDoesNotContainsDiallingCode() {
        String phoneNumberWithoutDiallingCode = "123456789";
        assertFalse(smsService.validatePhoneNumber(phoneNumberWithoutDiallingCode));
    }

    @Test
    void validatePhoneNumberShouldReturnFalseWhenPhoneNumberInvalidLength() {
        String tooShortPhoneNumber = "+4812345678";
        String tooLongPhoneNumber = "+481234567890";

        assertFalse(smsService.validatePhoneNumber(tooShortPhoneNumber));
        assertFalse(smsService.validatePhoneNumber(tooLongPhoneNumber));
    }

    @Test
    void validateMessageShouldReturnTrueWhenMessageCorrect() {
        String correctMessage = "Hello World!";
        assertTrue(smsService.validateMessage(correctMessage));
    }

    @Test
    void validateMessageShouldReturnFalseWhenMessageIsBlank() {
        String blankMessage = "";
        assertFalse(smsService.validateMessage(blankMessage));
    }
}