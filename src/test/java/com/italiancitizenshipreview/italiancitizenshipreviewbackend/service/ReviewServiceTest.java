package com.italiancitizenshipreview.italiancitizenshipreviewbackend.service;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.ReviewRepository;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.ServiceProviderRepository;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ReviewService;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ServiceProviderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ServiceProviderRepository serviceProviderRepository;

    @Mock
    private ServiceProviderService serviceProviderService;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createReview_ShouldSaveReviewAndReturnIt() {
        // Arrange
        String title = "Test Title";
        String content = "Test Content";
        Long userId = 1L;
        Long serviceProviderId = 1L;
        int rating = 5;



        Review review = new Review();
        ServiceProvider mockServiceProvider = Mockito.mock(ServiceProvider.class);
        mockServiceProvider.setServiceProviderId(serviceProviderId);
        review.setTitle(title);
        review.setContent(content);
        review.setUserId(userId);
        review.setServiceProvider(mockServiceProvider); // Create a mock ServiceProvider object
        review.setDate(Date.valueOf(LocalDate.now()));
        review.setRating(rating);

        when(serviceProviderService.getServiceProvider(serviceProviderId)).thenReturn(mockServiceProvider); // Mock the ServiceProviderService to return a mock ServiceProvider object
        when(reviewRepository.save(review)).thenReturn(review);

        // Act
        Review createdReview = reviewService.createReview(title, content, userId, serviceProviderId, rating);

        // Assert
        assertEquals(title, createdReview.getTitle());
        assertEquals(content, createdReview.getContent());
        assertEquals(userId, createdReview.getUserId());
        assertEquals(rating, createdReview.getRating());

        verify(reviewRepository, times(1)).save(review);
    }


    @Test
    public void deleteById_ShouldDeleteReview() {
        // Arrange
        Long reviewId = 1L;

        // Act
        reviewService.deleteById(reviewId);

        // Assert
        verify(reviewRepository, times(1)).deleteById(reviewId);
    }

    @Test
    public void findAllReviewsByUserId_ShouldReturnListOfReviews() {
        // Arrange
        Long userId = 1L;
        List<Review> expectedReviews = new ArrayList<Review>();
        Review review1 = new Review();
        Review review2 = new Review();
        review1.setUserId(userId);
        review2.setUserId(userId);
        expectedReviews.add(review1);
        expectedReviews.add(review2);

        when(reviewRepository.findAllByUserId(userId)).thenReturn(expectedReviews);

        // Act
        List<Review> actualReviews = reviewService.findAllReviewsByUserId(userId);

        // Assert
        assertEquals(expectedReviews.size(), actualReviews.size());
        assertEquals(expectedReviews, actualReviews);
    }

    @Test
    public void updateReview_ShouldSaveUpdatedReview() {
        // Arrange
        Review review = new Review();

        // Act
        reviewService.updateReview(review);

        // Assert
        verify(reviewRepository, times(1)).save(review);
    }
}
