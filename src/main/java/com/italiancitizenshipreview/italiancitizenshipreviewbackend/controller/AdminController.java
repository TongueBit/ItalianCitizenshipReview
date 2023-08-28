package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.User;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ReviewService;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ServiceProviderService;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ServiceProviderService serviceProviderService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user/dashboard/admin/")
    public String getReviewsForApproval(Model model, HttpServletRequest request) {
        List<ServiceProvider> serviceProviders = serviceProviderService.findAllByApproved(false);
        List<Review> reviews = reviewService.findAllByApproved(false);
        model.addAttribute("reviews", reviews);
        model.addAttribute("serviceProviders", serviceProviders);
        model.addAttribute("request", request);
        return "admin";
    }

    @GetMapping("/load")
    public String test(Model model) throws JsonProcessingException {
        reviewService.updateReviews();
        model.addAttribute("user", new User());
        return "index";
    }
}
