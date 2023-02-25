package com.example.twiliosendsms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SendSmsController {

    private final NewService newService;

    @Autowired
    public SendSmsController(NewService newService) {
        this.newService = newService;
    }

    @PostMapping
    public void sendSms(@RequestBody SmsRequest smsRequest) {
        newService.sendSms(smsRequest);
    }
}
