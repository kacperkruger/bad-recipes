package com.github.kacperkruger.notificationsms.sender;

import com.github.kacperkruger.notificationsms.domain.SMSRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service("twilio")
public class TwilioSMSSender implements SMSSender {

    private final String twilioPhoneNumber;

    public TwilioSMSSender(String twilioPhoneNumber) {
        this.twilioPhoneNumber = twilioPhoneNumber;
    }

    @Override
    public void send(SMSRequest smsRequest) {
        Message.creator(
                new PhoneNumber(smsRequest.getPhoneNumber()),
                new PhoneNumber(twilioPhoneNumber),
                smsRequest.getMessage()
        ).create();
    }
}
