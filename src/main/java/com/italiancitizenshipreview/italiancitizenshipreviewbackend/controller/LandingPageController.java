package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ServiceProviderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;



@Controller
public class LandingPageController {

    @Autowired
    private ServiceProviderService serviceProviderService;

    @GetMapping("/landing-page")
    public String homePage(Model model, HttpServletRequest request) {
        List<ServiceProvider> serviceProviders = serviceProviderService.getAllServiceProviders();
        model.addAttribute("serviceProviders", serviceProviders);
        model.addAttribute("request", request);
        return "landing-page";
    }

    @GetMapping("/js/landing-page.js")
    public ResponseEntity<Resource> getHomeJs() {
        // Load and return the "home.js" file as a resource
        Resource resource = new ClassPathResource("static/js/landing-page.js");
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/javascript"))
                .body(resource);
    }
}