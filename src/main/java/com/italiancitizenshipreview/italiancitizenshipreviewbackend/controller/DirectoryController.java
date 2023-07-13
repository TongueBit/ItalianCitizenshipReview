package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ServiceProviderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class DirectoryController {

    @Autowired
    private ServiceProviderService serviceProviderService;



    @GetMapping("/service-provider/all")
    public String homePage(Model model, HttpServletRequest request) {
        List<ServiceProvider> serviceProviders = serviceProviderService.getAllServiceProviders();
        serviceProviders.sort((o1, o2) -> o2.getAvgRating().compareTo(o1.getAvgRating()));
        model.addAttribute("serviceProviders", serviceProviders);
        model.addAttribute("request", request);

        return "directory-new";
    }
}
