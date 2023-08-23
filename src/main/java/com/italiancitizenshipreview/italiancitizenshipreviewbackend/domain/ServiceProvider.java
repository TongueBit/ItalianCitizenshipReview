package com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain;


import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ServiceProvider {
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;


    @OneToMany(mappedBy = "serviceProvider" ,fetch = FetchType.EAGER)
    private List<Review> reviews;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceProviderId;

    @Column(columnDefinition = "DECIMAL(2,1)")
    private BigDecimal avgRating;

    private boolean approved;
    private String email;

    private String website;

    private String phone;

    private String logoUrl;
    private int lowestEstimate;
    private int highestEstimate;
    private String services;
    private String googleId;



    public ServiceProvider() {
    }
    public ServiceProvider(String name, String description ) {
        this.name = name;
        this.description = description;
        this.approved = false;
        this.avgRating = BigDecimal.ZERO;

    }

    public ServiceProvider(String name, String description, String email, String logoUrl,
                           int lowestEstimate, int highestEstimate, String services, String googleId, String website) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.logoUrl = logoUrl;
        this.lowestEstimate = lowestEstimate;
        this.highestEstimate = highestEstimate;
        this.services = services;
        avgRating = BigDecimal.ZERO;
        this.googleId = googleId;
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }


    public void setAvgRating(BigDecimal avgRating) {
        this.avgRating = avgRating;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public int getLowestEstimate() {
        return lowestEstimate;
    }

    public void setLowestEstimate(int lowestEstimate) {
        this.lowestEstimate = lowestEstimate;
    }

    public int getHighestEstimate() {
        return highestEstimate;
    }

    public void setHighestEstimate(int highestEstimate) {
        this.highestEstimate = highestEstimate;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved() {
        approved = true;
    }



    public BigDecimal getAvgRating() {
        return avgRating;
    }



    public Long getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(Long serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReviews(List<Review> reviews) {
        if (this.reviews == null) {
            this.reviews = new ArrayList<>();
        }
        this.reviews.addAll(reviews);
    }

    public Boolean hasAPI() {
        return googleId != null ;
    }

    public void callAPI() {
        if (googleId != null) {
            System.out.println("Calling Google API");
        }
    }

    @Override
    public String toString() {
        return "ServiceProvider{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                 '\'' +
                '}';
    }


}
