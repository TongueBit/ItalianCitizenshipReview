package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.ReviewRepository;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ReviewService;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ServiceProviderService serviceProviderService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{reviewId}")
    public String deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteById(reviewId);
        return "redirect:/service-provider/all";
    }

    @PostMapping("/edit/{serviceProviderId}")
    public String editReview(@PathVariable Long serviceProviderId, Review review) {
        ServiceProvider serviceProvider = serviceProviderService.getServiceProvider(serviceProviderId);
        review.setServiceProvider(serviceProvider);
        reviewService.updateReview(review);
        return "redirect:/user/dashboard/";
    }

}
