package com.italiancitizenshipreview.italiancitizenshipreviewbackend.dto;

import java.math.BigDecimal;

public class ServiceProviderResponse {
    private String name;
    private String description;

    private BigDecimal rating;

    private Long serviceProviderId;

    private String logoUrl;

    public ServiceProviderResponse(String name, String description, BigDecimal rating, Long serviceProviderId, String  logoUrl) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.serviceProviderId = serviceProviderId;
        this.logoUrl = logoUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
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

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }
}
