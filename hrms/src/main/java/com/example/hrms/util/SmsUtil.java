package com.example.hrms.util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Component;

@Component
public class SmsUtil {

    private final String ACCOUNT_SID = "YOUR_TWILIO_SID";
    private final String AUTH_TOKEN = "YOUR_TWILIO_AUTH";
    private final String FROM_PHONE = "+1234567890";

    public SmsUtil() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendSms(String to, String message) {
        Message.creator(
                new com.twilio.type.PhoneNumber(to),
                new com.twilio.type.PhoneNumber(FROM_PHONE),
                message
        ).create();
    }
}
