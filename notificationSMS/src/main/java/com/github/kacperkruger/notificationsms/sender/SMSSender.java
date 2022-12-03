package com.github.kacperkruger.notificationsms.sender;

import com.github.kacperkruger.notificationsms.domain.SMSRequest;

public interface SMSSender {

    void send(SMSRequest smsRequest);
}
