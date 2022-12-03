package com.github.kacperkruger.notificationsms.service;

import com.github.kacperkruger.notificationsms.domain.SMSRequest;
import com.github.kacperkruger.notificationsms.sender.SMSSender;
import com.github.kacperkruger.notificationsms.service.error.InvalidMessage;
import com.github.kacperkruger.notificationsms.service.error.InvalidPhoneNumber;
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

    public void validateAndSend(SMSRequest smsRequest) throws InvalidPhoneNumber, InvalidMessage {
        if (!validatePhoneNumber(smsRequest.getPhoneNumber())) throw new InvalidPhoneNumber();
        if (!validateMessage(smsRequest.getMessage())) throw new InvalidMessage();
        smsSender.send(smsRequest);
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }

    public boolean validateMessage(String message) {
        return !message.isEmpty();
    }
}
