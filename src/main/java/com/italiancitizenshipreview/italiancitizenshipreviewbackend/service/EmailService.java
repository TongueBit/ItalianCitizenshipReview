package com.italiancitizenshipreview.italiancitizenshipreviewbackend.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Component
@EnableAsync
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    private static final int REGISTRATION_CODE_LENGTH = 6;
    private static final int REGISTRATION_CODE_VALIDITY_MINUTES = 5;
    private static final Map<String, LocalDateTime> registrationCodes = new HashMap<>();

    @Async
    public void sendRegistrationEmail(String to) {
        String registrationCode = generateRegistrationCode();

        String subject = "Registration Code";
        String body = "Your registration code is: " + registrationCode;

        sendEmail(to, subject, body);

        // Store the registration code and its expiration time
        LocalDateTime expirationTime = LocalDateTime.now().plus(REGISTRATION_CODE_VALIDITY_MINUTES, ChronoUnit.MINUTES);
        registrationCodes.put(registrationCode, expirationTime);

    }

    public boolean verifyRegistrationCode(String code) {
        LocalDateTime expirationTime = registrationCodes.get(code);
        if (expirationTime != null && LocalDateTime.now().isBefore(expirationTime)) {
            // Code is valid and not expired
            return true;
        }
        // Code is invalid or expired
        return false;
    }

    // Scheduled task to remove expired registration codes
    @Scheduled(fixedRate = 60000) // Run every minute
    public void removeExpiredRegistrationCodes() {
        LocalDateTime currentTime = LocalDateTime.now();
        registrationCodes.entrySet().removeIf(entry -> entry.getValue().isBefore(currentTime));
    }

    // Other methods...

    private String generateRegistrationCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < REGISTRATION_CODE_LENGTH; i++) {
            int digit = random.nextInt(10);
            code.append(digit);
        }
        return code.toString();
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("register@italiancitizenshipreview.com"); // Set the "From" address
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }


}
