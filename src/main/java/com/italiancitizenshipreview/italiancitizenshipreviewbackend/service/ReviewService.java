package com.italiancitizenshipreview.italiancitizenshipreviewbackend.service;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
                               int rating)
    {
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

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public void deleteById(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
    public List<Review> findAllReviewsByUserId(Long userId) {
        return reviewRepository.findAllByUserId(userId);
    }
    public void updateReview(Review review) {
        reviewRepository.save(review);
    }

    public List<Review> findAllByApproved(boolean approved) {
        return reviewRepository.findAllByApproved(approved);
    }

    public void setApprovedByReviewId(Long reviewId) {
       Review review = reviewRepository.findByReviewId(reviewId);
       review.setApproved();
       reviewRepository.save(review);
    }
}
