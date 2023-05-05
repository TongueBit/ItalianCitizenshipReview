package com.italiancitizenshipreview.italiancitizenshipreviewbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
