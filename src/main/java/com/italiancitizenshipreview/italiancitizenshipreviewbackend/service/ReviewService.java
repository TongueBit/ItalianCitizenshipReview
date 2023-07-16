package com.italiancitizenshipreview.italiancitizenshipreviewbackend.service;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.ReviewRepository;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ServiceProviderService serviceProviderService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

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

    public void filterReviewsByNewest(ServiceProvider serviceProvider) {
        List<Review> reviews = serviceProvider.getReviews();

        reviews.sort(Comparator.comparing(Review::getDate, Comparator.nullsLast(Comparator.reverseOrder())));
    }

    public boolean hasUserMadeReview(Long serviceProviderId, Long userId) {
        ServiceProvider serviceProvider = serviceProviderRepository.findByServiceProviderId(serviceProviderId);
        List<Review> reviews = serviceProvider.getReviews();

        for (Review review : reviews) {
            if (review.getUserId().equals(userId)) {
                return true; // User has made a review for the service provider
            }
        }
        return false; // User has not made a review for the service provider
    }
}
