package com.github.kacperkruger.notificationSMS.config;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {

    @Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.phone_number}")
    private String twilioPhoneNumber;

    @Bean
    public void twilioInitializer() {
        Twilio.init(
                accountSid,
                authToken
        );
    }

    @Bean
    public String twilioPhoneNumber() {
        return twilioPhoneNumber;
    }
}
