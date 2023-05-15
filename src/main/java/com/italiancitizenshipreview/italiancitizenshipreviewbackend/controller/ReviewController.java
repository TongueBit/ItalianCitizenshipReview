package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.ReviewRepository;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ReviewService;
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{reviewId}")
    public String deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteById(reviewId);
        return "redirect:/service-provider/all";
    }
}
