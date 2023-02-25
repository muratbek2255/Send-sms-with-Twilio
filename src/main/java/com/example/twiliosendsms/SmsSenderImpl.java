package com.example.twiliosendsms;


import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.rest.api.v2010.account.Message;

import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("twilio")
public class SmsSenderImpl implements SmsSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsSenderImpl.class);

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public SmsSenderImpl(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendSms(SmsRequest smsRequest) {
        if(smsRequest.getPhoneNumber().startsWith("+996")) {
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());

            MessageCreator messageCreator = Message.creator(to, from, smsRequest.getMessage());

            messageCreator.create();

            LOGGER.info("Send sms {}" + smsRequest);
        }else {
            throw new IllegalArgumentException("You need start with +996");
        }
    }
}
