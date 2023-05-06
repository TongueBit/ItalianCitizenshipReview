package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ServiceProviderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;


import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ServiceProviderService serviceProviderService;

    @GetMapping("/js/home.js")
    public ResponseEntity<Resource> getHomeJs() {
        // Load and return the "home.js" file as a resource
        Resource resource = new ClassPathResource("static/js/home.js");
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/javascript"))
                .body(resource);
    }

    @GetMapping("/css/home.css")
    public ResponseEntity<Resource> getHomeCss() {
        // Load and return the "home.js" file as a resource
        Resource resource = new ClassPathResource("static/css/home.css");
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/css"))
                .body(resource);
    }
    @GetMapping("/")
    public String homePageRedirect() {
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String homePage(Model model, HttpServletRequest request) {
        List<ServiceProvider> serviceProviders = serviceProviderService.getAllServiceProviders();
        model.addAttribute("serviceProviders", serviceProviders);
        model.addAttribute("request", request);

        return "home";
    }

    /**
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user")
    public String user() {
        return "Hello User";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin";
    }
    **/
}
