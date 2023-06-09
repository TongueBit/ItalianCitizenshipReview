package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.User;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ServiceProviderService;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ServiceProviderController {

    @Autowired
    private ServiceProviderService serviceProviderService;

    @Autowired
    private UserService userService;

    @GetMapping("/service-provider")
    public String getAddServiceProviderForm(Model map, HttpServletRequest request){
        map.addAttribute("serviceProvider", new ServiceProvider());
        map.addAttribute("user", new User());
        map.addAttribute("request", request);
        return "service-provider";
    }

    @PostMapping("/service-provider")
    public String addServiceProvider(String name, String description){

    ServiceProvider serviceProvider = serviceProviderService.createServiceProvider(name, description);

        return "redirect:/service-provider/ " + serviceProvider.getServiceProviderId();
    }

    @GetMapping("/service-provider/{serviceProviderId}")
    public String getServiceProviders(ModelMap model, @PathVariable Long serviceProviderId, HttpServletRequest request){
        ServiceProvider serviceProvider = serviceProviderService.getServiceProvider(serviceProviderId);
        model.put("serviceProvider", serviceProvider);
        model.put("request", request);
        return "service-provider";
    }

    @PostMapping("/service-provider/{serviceProviderId}")
    public String updateServiceProvider(ServiceProvider serviceProvider){
        serviceProviderService.updateServiceProvider(serviceProvider);
        return "redirect:/service-provider/" + serviceProvider.getServiceProviderId();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/approve/service-provider/{serviceProviderId}")
    public String approveServiceProvider(@PathVariable Long serviceProviderId) {
        serviceProviderService.setApprovedByServiceProviderId(serviceProviderId);
        return "redirect:/user/dashboard/admin/";
    }

}

