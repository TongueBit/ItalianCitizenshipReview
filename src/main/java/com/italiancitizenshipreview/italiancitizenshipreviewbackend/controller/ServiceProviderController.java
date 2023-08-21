package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.User;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.ServiceProviderRepository;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ReviewService;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ServiceProviderService;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@Controller
public class ServiceProviderController {

    @Autowired
    private ServiceProviderService serviceProviderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

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
        reviewService.filterReviewsByNewest(serviceProvider);
        model.put("serviceProvider", serviceProvider);
        model.put("request", request);
        return "service-provider";
    }

    @GetMapping("/service-provider/{serviceProviderId}/filter/stars/{filterRating}")
    public String getServiceProviders(ModelMap model, @PathVariable Long serviceProviderId, HttpServletRequest request, @PathVariable int filterRating){
        ServiceProvider serviceProvider = serviceProviderService.getServiceProvider(serviceProviderId);
        serviceProvider.getReviews().removeIf(review -> {
            return review.getRating() != filterRating;
        });
        reviewService.filterReviewsByNewest(serviceProvider);
        model.addAttribute("serviceProvider", serviceProvider);
        model.addAttribute("request", request);
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

    @GetMapping("/service-provider/register")
    public String formForCreatingNewServiceProvider(Model map, HttpServletRequest request) {
        map.addAttribute("serviceProvider", new ServiceProvider());
        map.addAttribute("user", new User());
        map.addAttribute("request", request);
        return "service-provider-register";
    }

    @PostMapping("/service-provider/register")
    public String saveServiceProvider(@RequestParam("name") String name,
                                      @RequestParam("email") String email,
                                      @RequestParam("description") String description,
                                      @RequestParam("logoUrl") String logoUrl,
                                      @RequestParam("lowestEstimate") int lowestEstimate,
                                      @RequestParam("highestEstimate") int highestEstimate,
                                      @RequestParam(value = "services", required = false) List<String> services,
                                      @RequestParam(value = "googleId", required = false) String googleId,
                                      @RequestParam(value = "facebookId", required = false) String facebookId,
                                      @RequestParam(value = "website", required = false) String website,
                                      Model map, HttpServletRequest request) {

        StringBuilder sb = new StringBuilder();
        for (String s : services) {
            sb.append(s).append(", ");
        }

        String concatenatedServices = sb.toString();
        if (concatenatedServices.endsWith(" ")) {
            concatenatedServices = concatenatedServices.substring(0, concatenatedServices.length() - 2);
        }

        serviceProviderService.registerServiceProvider(name, description, email, logoUrl, lowestEstimate, highestEstimate, concatenatedServices, googleId, facebookId, website);
        map.addAttribute("user", new User());
        map.addAttribute("request", request);
        map.addAttribute("serviceProvider", new ServiceProvider());
        return "service-provider-register"; // Replace "success" with the appropriate view name



    }

    @GetMapping("/service-provider/register/edit/{serviceProviderId}")
    public String editServiceProvider(
                                      @PathVariable  Long serviceProviderId,
                                      Model map, HttpServletRequest request) {

        ServiceProvider sp = serviceProviderService.getServiceProvider(serviceProviderId);
        map.addAttribute("serviceProvider", sp);
        map.addAttribute("user", new User());
        map.addAttribute("request", request);
        return "service-provider-register"; // Replace "success" with the appropriate view name


    }



}

