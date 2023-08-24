package com.italiancitizenshipreview.italiancitizenshipreviewbackend.service;

public class KeyValue {
    private String serviceProviderName;
    private int serviceProviderId;

    public KeyValue(String serviceProviderName, int serviceProviderId) {
        this.serviceProviderName = serviceProviderName;
        this.serviceProviderId = serviceProviderId;
    }

    public String getServiceProviderName() {
        return serviceProviderName;
    }

    public int getServiceProviderId() {
        return serviceProviderId;
    }
}
