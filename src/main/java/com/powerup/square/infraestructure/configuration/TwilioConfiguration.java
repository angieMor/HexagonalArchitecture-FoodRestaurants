package com.powerup.square.infraestructure.configuration;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@NoArgsConstructor
public class TwilioConfiguration {

    @Autowired
    private Environment env;

    public void sendSMS(String body) {
        Twilio.init(env.getProperty("twilio.account.sid"), env.getProperty("twilio.auth.token"));
        Message.creator(new PhoneNumber("+573013544376"), new PhoneNumber("+15075805292"), body).create();
    }

}
