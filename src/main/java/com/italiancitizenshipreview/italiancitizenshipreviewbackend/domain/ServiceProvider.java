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
    private String logoUrl;
    private int lowestEstimate;
    private int highestEstimate;
    private String services;

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

    public ServiceProvider(String name, String description ) {
        this.name = name;
        this.description = description;
        this.approved = false;
        this.avgRating = BigDecimal.ZERO;

    }
    public ServiceProvider() {
    }

    public ServiceProvider(String name, String description, String email, String logoUrl,
                           int lowestEstimate, int highestEstimate, String services) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.logoUrl = logoUrl;
        this.lowestEstimate = lowestEstimate;
        this.highestEstimate = highestEstimate;
        this.services = services;
        avgRating = BigDecimal.ZERO;
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

    @Override
    public String toString() {
        return "ServiceProvider{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                 '\'' +
                '}';
    }


}
