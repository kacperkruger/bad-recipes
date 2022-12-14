package com.github.kacperkruger.notificationSMS.service;

import com.github.kacperkruger.clients.notification.sms.domain.SMSRequest;
import com.github.kacperkruger.notificationSMS.sender.SMSSender;
import com.github.kacperkruger.notificationSMS.service.error.InvalidMessageException;
import com.github.kacperkruger.notificationSMS.service.error.InvalidPhoneNumberException;
import com.github.kacperkruger.notificationSMS.service.error.SMSRequestException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class NotificationSMSService {

    private final Pattern PHONE_PATTERN = Pattern.compile("^[+][0-9]{11}");

    private final SMSSender smsSender;

    public NotificationSMSService(@Qualifier("twilio") SMSSender smsSender) {
        this.smsSender = smsSender;
    }

    public void validateAndSend(SMSRequest smsRequest) throws SMSRequestException, InterruptedException {
        validateSMSRequest(smsRequest);
        smsSender.send(smsRequest);
    }

    public void validateSMSRequest(SMSRequest smsRequest) throws SMSRequestException {
        if (!isPhoneNumberCorrect(smsRequest.getPhoneNumber())) throw new InvalidPhoneNumberException();
        if (!isMessageNotEmpty(smsRequest.getMessage())) throw new InvalidMessageException();
    }

    public boolean isPhoneNumberCorrect(String phoneNumber) {
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }

    public boolean isMessageNotEmpty(String message) {
        return !message.isEmpty();
    }
}
