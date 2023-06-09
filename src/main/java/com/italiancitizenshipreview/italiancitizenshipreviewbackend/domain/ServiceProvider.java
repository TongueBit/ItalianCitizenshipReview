package com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain;


import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
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

    }
    public ServiceProvider() {
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
