package com.italiancitizenshipreview.italiancitizenshipreviewbackend.service;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class ReviewService {

    @Autowired
    private ServiceProviderService serviceProviderService;

    @Autowired
    private ReviewRepository reviewRepository;

    public Review createReview(String title,
                               String content,
                               Long userId,
                               Long serviceProviderId,
                               int rating) {
        Review review = new Review();
        review.setTitle(title);
        review.setContent(content);
        review.setUserId(userId);
        review.setServiceProvider(serviceProviderService.getServiceProvider(serviceProviderId));
        review.setDate(Date.valueOf(LocalDate.now()));
        review.setRating(rating);
        reviewRepository.save(review);
        return review;
    }


}
