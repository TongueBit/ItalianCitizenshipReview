package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


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

    ServiceProvider serviceProvider = serviceProviderService.createServiceProvider(name, description);

        return "redirect:/service-provider/ " + serviceProvider.getServiceProviderId();
    }

    @GetMapping("/service-provider/{serviceProviderId}")
    public String getServiceProviders(ModelMap model, @PathVariable Long serviceProviderId){
        ServiceProvider serviceProvider = serviceProviderService.getServiceProvider(serviceProviderId);
        model.put("serviceProvider", serviceProvider);
        return "service-provider";
    }

    @PostMapping("/service-provider/{serviceProviderId}")
    public String updateServiceProvider(ServiceProvider serviceProvider){
        serviceProviderService.updateServiceProvider(serviceProvider);
        return "redirect:/service-provider/" + serviceProvider.getServiceProviderId();
    }


}

