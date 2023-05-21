package com.italiancitizenshipreview.italiancitizenshipreviewbackend.service;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.SecurityUser;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.User;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.ReviewRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
}
