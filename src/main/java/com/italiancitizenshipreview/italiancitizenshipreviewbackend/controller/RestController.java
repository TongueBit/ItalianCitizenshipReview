package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.dto.ReviewRequest;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ReviewService;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class RestController {

    @Autowired
    private ServiceProviderService serviceProviderService;
    @GetMapping("/service-provider/{serviceProviderId}")
    public ServiceProvider getServiceProvider(@PathVariable Long serviceProviderId) {
        ServiceProvider sp = serviceProviderService.getOneServiceProviderWithReviews(serviceProviderId);
        return sp;
    }

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest reviewRequest) {

        // Create a new Review object
        Review review = new Review();
        review.setServiceProvider(serviceProviderService.getServiceProvider(reviewRequest.getServiceProviderId()));
        review.setTitle(reviewRequest.getTitle());
        review.setContent(reviewRequest.getContent());
        review.setRating(reviewRequest.getRating());
        review.setUserId(reviewRequest.getUserId());

        // Save the review using the reviewService
        Review createdReview = reviewService.createReview(review);

        // Return the created review in the response body with a status of 201 (Created)
        return ResponseEntity.ok().build();
    }



}

