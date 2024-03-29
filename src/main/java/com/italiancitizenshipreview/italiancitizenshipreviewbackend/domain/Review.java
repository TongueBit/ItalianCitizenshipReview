package com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private Long userId;
    private String authorName;

    @ManyToOne
    @JoinColumn(name = "serviceProviderId")
    @JsonBackReference
    private ServiceProvider serviceProvider;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private int rating;

    @Column(name = "approved")
    private boolean approved;

    private String googlePhoto;

    private String facebookPhoto;

    private Boolean recommended;

    public Review() {
    }

    public Review(String content, ServiceProvider serviceProvider, int rating, String authorName) {
        this.content = content;
        this.serviceProvider = serviceProvider;
        this.rating = rating;
        this.authorName = authorName;
    }

    public Review(String authorName, Date date, String content, Boolean recommended, int rating, ServiceProvider serviceProvider) {
        this.authorName = authorName;

        this.date = date;
        this.content = content;
        this.recommended = recommended;
        this.rating = rating;
        this.serviceProvider = serviceProvider;
    }

    public String getFacebookPhoto() {
        return facebookPhoto;
    }

    public void setFacebookPhoto(String facebookPhoto) {
        this.facebookPhoto = facebookPhoto;
    }

    public Boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getGooglePhoto() {
        return googlePhoto;
    }

    public void setGooglePhoto(String googlePhoto) {
        this.googlePhoto = googlePhoto;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved() {
        approved = true;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String getDate() {
        if(date == null) {
            return "";
        }
        if(date.toString().contains("00:00:00.0")) {
           String dateString = date.toString();
           dateString = dateString.replace("00:00:00.0", "");
            return dateString;
        }


        return date.toString();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", serviceProvider=" + serviceProvider +
                ", date='" + date + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return rating == review.rating && Objects.equals(reviewId, review.reviewId) && Objects.equals(title, review.title) && Objects.equals(content, review.content) && Objects.equals(userId, review.userId) && Objects.equals(serviceProvider, review.serviceProvider) && Objects.equals(date, review.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, title, content, userId, serviceProvider, date, rating);
    }
}
