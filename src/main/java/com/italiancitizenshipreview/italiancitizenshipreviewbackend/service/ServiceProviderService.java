package com.italiancitizenshipreview.italiancitizenshipreviewbackend.service;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ServiceProviderService {

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;


    public ServiceProvider createServiceProvider(String name, String description) {
        ServiceProvider serviceProvider = new ServiceProvider(name, description);
        serviceProvider.setServices("");
         return serviceProviderRepository.save(serviceProvider);
    }

    public void registerServiceProvider(String name, String description, String email, String logoUrl, int lowestEstimate, int highestEstimate, String services, String googleId, String facebookId) {

        if(  getServiceProviderByName(name) != null){
            ServiceProvider sp = getServiceProviderByName(name);
            sp.setGoogleId(googleId);
            sp.setFacebookId(facebookId);
            serviceProviderRepository.save(sp);
        }
        else {
            ServiceProvider serviceProvider = new ServiceProvider(name, description, email, logoUrl, lowestEstimate, highestEstimate, services, googleId, facebookId);
            serviceProviderRepository.save(serviceProvider);
        }
    }

    public ServiceProvider getServiceProvider(Long serviceProviderId) {
        return serviceProviderRepository.findByServiceProviderId(serviceProviderId);
    }

    public ServiceProvider getOneServiceProviderWithReviews(Long serviceProviderId) {
        return serviceProviderRepository.findOneWithReviews(serviceProviderId);
    }

    public void updateServiceProvider(ServiceProvider serviceProvider) {
        serviceProviderRepository.save(serviceProvider);
    }

    public List<ServiceProvider> getAllServiceProviders() {
        Iterable<ServiceProvider> serviceProviders = serviceProviderRepository.findAllServiceProvidersWithReviews();
        return (List<ServiceProvider>) serviceProviders;

    }

    public void printReviews(ServiceProvider serviceProvider){
        System.out.println(serviceProvider.getReviews());
    }

    public List<ServiceProvider> getAllServiceProviderswithoutReviews() {
        return serviceProviderRepository.findAllServiceProviders();
    }

    public List<ServiceProvider> findAllByApproved(boolean b) {
        return serviceProviderRepository.findAllByApproved(b);
    }

    public void setApprovedByServiceProviderId(Long serviceProviderId) {
        ServiceProvider serviceProvider = serviceProviderRepository.findByServiceProviderId(serviceProviderId);
        serviceProvider.setApproved();
        serviceProviderRepository.save(serviceProvider);
    }

    public ServiceProvider getServiceProviderByName(String name) {
        int i = 0;
        return serviceProviderRepository.findServiceProviderByName(name);
    }
}
