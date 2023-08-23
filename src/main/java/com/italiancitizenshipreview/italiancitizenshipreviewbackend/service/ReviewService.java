package com.italiancitizenshipreview.italiancitizenshipreviewbackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.dto.CardResponse;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.ReviewRepository;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private static final String GOOGLE_PLACES_API_KEY = System.getenv("GOOGLE_PLACES_API_KEY");

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
        reviewRepository.save(review);
        ServiceProvider sp = review.getServiceProvider();
        List<Review> reviews = sp.getReviews();
        double averageRating = reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0);
        sp.setAvgRating(BigDecimal.valueOf(averageRating));
        serviceProviderRepository.save(sp); //

        return review;
    }

    public void deleteById(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
    public List<Review> findAllReviewsByUserId(Long userId) {
        return reviewRepository.findAllByUserId(userId);
    }
    public void updateReview(Review review) {
        reviewRepository.save(review);

        ServiceProvider sp = review.getServiceProvider();
        List<Review> reviews = sp.getReviews();
        double averageRating = reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0);
        sp.setAvgRating(BigDecimal.valueOf(averageRating));
        serviceProviderRepository.save(sp); //
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
            if (review.getUserId() != null && review.getUserId().equals(userId)) {
                return true; // User has made a review for the service provider
            }
        }
        return false; // User has not made a review for the service provider
    }

    public void updateReviews() throws JsonProcessingException {
        List<ServiceProvider> serviceProviders = serviceProviderRepository.findAllServiceProviders();
        for (ServiceProvider serviceProvider : serviceProviders) {
            updateServiceProviderReviews(serviceProvider);
        }

        /**
        List<ServiceProvider> serviceProviders = getServiceProviderWithNonNullIds();
        List<ServiceProvider> updatedServiceProviders = serviceProviders.stream()
                .map(serviceProvider -> {
                    try {
                        return updateServiceProviderReviews(serviceProvider);
                    } catch (JsonProcessingException e) {
                        // Handle the exception here
                        return null;
                    }
                })
                .collect(Collectors.toList());
        */
    }

    private List<ServiceProvider> getServiceProviderWithNonNullIds() {
        serviceProviderService.getAllServiceProviders();
        return new ArrayList<>();
    }

    private ServiceProvider updateServiceProviderReviews(ServiceProvider serviceProvider) throws JsonProcessingException {
        String googleId = serviceProvider.getGoogleId();
        String facebookId = serviceProvider.getFacebookId();
        if (!googleId.isEmpty()) {
            updateGoogleReviews(serviceProvider);
        } else if (!facebookId.isEmpty()) {


            //add facebook call
            //
            //
        }
        return serviceProvider;
    }

    private void updateGoogleReviews(ServiceProvider serviceProvider) throws JsonProcessingException {
        List<Review> existingReviews = serviceProvider.getReviews();
        String apiKey = System.getenv("GOOGLE_PLACES_API_KEY");
        String locationId = serviceProvider.getGoogleId();
        String url = "https://maps.googleapis.com/maps/api/place/details/json?place_id=" + locationId + "&fields=review&key=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String responseBody = response.getBody();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseJson = mapper.readTree(responseBody);
        JsonNode reviews = responseJson.get("result").get("reviews");

        List<Review> reviewList = new ArrayList<>();
        for (JsonNode review : reviews) {
            boolean reviewExists = existingReviews.stream().anyMatch(existingReviewObj ->
                    existingReviewObj.getAuthorName().equals(review.get("author_name").asText()) &&
                            existingReviewObj.getContent().equals(review.get("text").asText())
            );
            if (!reviewExists) {
                Review reviewObj = new Review(review.get("text").asText(), serviceProvider, review.get("rating").asInt(), review.get("author_name").asText());
                reviewObj.setGooglePhoto(review.get("profile_photo_url").asText());
                reviewRepository.save(reviewObj);
                reviewList.add(reviewObj);
            }
        }
        serviceProvider.setReviews(reviewList);
        serviceProviderRepository.save(serviceProvider);
    }



}
