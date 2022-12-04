package com.github.kacperkruger.clients.notification.sms;

public class SMSRequest {

    private String phoneNumber;

    private String message;

    public SMSRequest() {
    }

    public SMSRequest(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
