package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.dto.ReviewRequest;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.dto.ServiceProviderResponse;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.dto.UserRegistrationRequest;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.EmailService;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ReviewService;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ServiceProviderService;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class RestController {
    @Autowired
    private UserService userService;
    @Autowired
    private ServiceProviderService serviceProviderService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private EmailService emailService;

    @GetMapping("/service-provider/{serviceProviderId}")
    public ServiceProvider getServiceProvider(@PathVariable Long serviceProviderId) {
        ServiceProvider sp = serviceProviderService.getOneServiceProviderWithReviews(serviceProviderId);
        return sp;
    }
    @PostMapping("/review")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest reviewRequest) {

        // Create a new Review object
        Review review = new Review();
        review.setServiceProvider(serviceProviderService.getServiceProvider(reviewRequest.getServiceProviderId()));
        review.setTitle(reviewRequest.getTitle());
        review.setContent(reviewRequest.getContent());
        review.setRating(reviewRequest.getRating());
        review.setUserId(reviewRequest.getUserId());
        review.setDate(new Date());

        // Save the review using the reviewService
        Review createdReview = reviewService.createReview(review);
        /** I want to eventually return the review object but the persitant bag thing has it all messed up. **/

        // Return the created review in the response body with a status of 201 (Created)
        return ResponseEntity.ok().build();
    }
    @GetMapping("user/exists/{username}")
    public boolean userExists(@PathVariable String username) {
        return userService.existsByUsername(username);
    }

    @GetMapping("/service-provider")
    public List<ServiceProviderResponse> getServiceProviders() {
        List<ServiceProviderResponse> serviceProviderRequests = new ArrayList<ServiceProviderResponse>();
        List<ServiceProvider> serviceProviders = serviceProviderService.getAllServiceProviderswithoutReviews();
        for (ServiceProvider sp : serviceProviders) {
            serviceProviderRequests.add(new ServiceProviderResponse(sp.getName(), sp.getDescription(), sp.getAvgRating(), sp.getServiceProviderId()));
        }
        return serviceProviderRequests;
    }

    @GetMapping("/register/{email}")
    public ResponseEntity<String> register(@PathVariable String email) {
        emailService.sendRegistrationEmail(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/verify/{code}")
    public ResponseEntity<String> verifyCode( @PathVariable String code) {
        if (emailService.verifyRegistrationCode(code)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        String email = request.getEmail();
        userService.createUser(username, password, email, "ROLE_USER");
        return ResponseEntity.ok().build();
    }
}

