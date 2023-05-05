package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ServiceProviderController {

    @Autowired
    private ServiceProviderService serviceProviderService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/service-provider")
    public String getAddServiceProviderForm(){
        return "service-provider";
    }

    @PostMapping("/service-provider")
    public String addServiceProvider(String name, String description){

    serviceProviderService.createServiceProvider(name, description);

        return "redirect:/service-provider";
    }
       // return "redirect:/service-provider/{serviceProviderId}";


    @GetMapping("/service-provider/{serviceProviderId}")
    public String getServiceProviders(@PathVariable Long serviceProviderId){
        return "service-provider";
    }

}
