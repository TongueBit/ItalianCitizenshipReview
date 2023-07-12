package com.italiancitizenshipreview.italiancitizenshipreviewbackend.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void shouldSendTestEmail() {
        String to = "tonyledmonds@gmail.com";
        String subject = "Test Email";
        String body = "This is a test email sent using Amazon SES.";

        emailService.sendEmail(to, subject, body);
    }
}
