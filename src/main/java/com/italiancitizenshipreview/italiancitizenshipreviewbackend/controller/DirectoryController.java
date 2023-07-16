package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ReviewService;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ServiceProviderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.math.BigDecimal;
import java.util.List;

@Controller
public class DirectoryController {

    @Autowired
    private ServiceProviderService serviceProviderService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/service-provider/all")
    public String directoryPage(Model model, HttpServletRequest request) {
        List<ServiceProvider> serviceProviders = serviceProviderService.getAllServiceProviders();
        serviceProviders.sort((o1, o2) -> o2.getAvgRating().compareTo(o1.getAvgRating()));
        model.addAttribute("serviceProviders", serviceProviders);
        model.addAttribute("request", request);

        return "directory";
    }

    @GetMapping("/service-provider/filter/stars/{rating}")
    public String filterByStars(Model model, HttpServletRequest request, @PathVariable("rating") int rating) {
        List<ServiceProvider> serviceProviders = serviceProviderService.getAllServiceProviders();
        serviceProviders.removeIf(provider -> {
                BigDecimal avgRating = provider.getAvgRating();
                int roundedAvgRating = Math.round(avgRating.floatValue());
                return roundedAvgRating != rating;
            });
        for (ServiceProvider serviceProvider : serviceProviders) {
            reviewService.filterReviewsByNewest(serviceProvider);
        }
        model.addAttribute("serviceProviders", serviceProviders);
        model.addAttribute("request", request);

        return "directory";

    }
}
