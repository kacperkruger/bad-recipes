package com.github.kacperkruger.notificationSMS.sender;

import com.github.kacperkruger.notificationSMS.domain.SMSRequest;

public interface SMSSender {

    void send(SMSRequest smsRequest);
}
