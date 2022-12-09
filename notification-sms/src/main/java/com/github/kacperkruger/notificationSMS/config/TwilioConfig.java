package com.github.kacperkruger.notificationSMS.config;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {

    @Value("${twilio.account_sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth_token}")
    private String AUTH_TOKEN;

    @Value("${twilio.phone_number}")
    private String TWILIO_PHONE_NUMBER;

    @Bean
    public void twilioInitializer() {
        Twilio.init(
                ACCOUNT_SID,
                AUTH_TOKEN
        );
    }

    @Bean
    public String twilioPhoneNumber() {
        return TWILIO_PHONE_NUMBER;
    }
}
