package com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain;


import jakarta.persistence.*;

@Entity
public class ServiceProvider {
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceProviderId;


    public ServiceProvider(String name, String description ) {
        this.name = name;
        this.description = description;

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

    @Override
    public String toString() {
        return "ServiceProvider{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                 '\'' +
                '}';
    }


}
