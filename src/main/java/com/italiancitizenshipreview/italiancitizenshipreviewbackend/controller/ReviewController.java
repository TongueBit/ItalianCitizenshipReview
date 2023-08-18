package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.User;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.ReviewRepository;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ReviewService;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.net.HttpCookie;
import java.net.http.HttpRequest;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ServiceProviderService serviceProviderService;

    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
    @GetMapping("/{reviewId}")
    public String deleteReview(@PathVariable("reviewId") Long reviewId) {

        reviewService.deleteById(reviewId);
        return "redirect:/user/dashboard/";
    }

    @PostMapping("/edit/{serviceProviderId}")
    public String editReview(@PathVariable Long serviceProviderId, Review review) {
        ServiceProvider serviceProvider = serviceProviderService.getServiceProvider(serviceProviderId);
        review.setServiceProvider(serviceProvider);
        reviewService.updateReview(review);
        return "redirect:/user/dashboard/";
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/approve/{reviewId}")
    public String approveReview(@PathVariable Long reviewId) {
        reviewService.setApprovedByReviewId(reviewId);

        return "redirect:/user/dashboard/admin/";
    }

    @GetMapping("/test")
    public String test(Model model) throws JsonProcessingException {
        reviewService.updateReviews();
        model.addAttribute("user", new User());
        return "service-provider-register";
    }
}
