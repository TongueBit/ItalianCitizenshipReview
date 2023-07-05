package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.dto.UserRegistrationRequest;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserRegistrationRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        userService.createUser(username, password, "ROLE_USER");
        return "login";
    }
}