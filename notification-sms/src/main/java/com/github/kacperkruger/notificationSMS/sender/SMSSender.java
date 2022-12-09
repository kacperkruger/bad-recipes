package com.github.kacperkruger.notificationSMS.sender;

import com.github.kacperkruger.clients.notification.sms.domain.SMSRequest;

public interface SMSSender {

    void send(SMSRequest smsRequest) throws InterruptedException;
}
