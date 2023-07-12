package com.italiancitizenshipreview.italiancitizenshipreviewbackend.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private static final int REGISTRATION_CODE_LENGTH = 6;
    private static final int REGISTRATION_CODE_VALIDITY_MINUTES = 5;


    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String sendRegistrationEmail(String to) {
        String registrationCode = generateRegistrationCode();

        String subject = "Registration Code";
        String body = "Your registration code is: " + registrationCode;

        sendEmail(to, subject, body);

        return registrationCode;
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("register@italiancitizenshipreview.com"); // Set the "From" address
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }

    private String generateRegistrationCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < REGISTRATION_CODE_LENGTH; i++) {
            int digit = random.nextInt(10);
            code.append(digit);
        }
        return code.toString();
    }

}
