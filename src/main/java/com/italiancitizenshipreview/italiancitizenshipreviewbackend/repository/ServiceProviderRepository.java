package com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.dto.ServiceProviderResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.security.Provider;
import java.util.List;

public interface ServiceProviderRepository extends CrudRepository<ServiceProvider, Long> {

    List<ServiceProvider> findAllByApproved(boolean approved);


    @Query("SELECT sp FROM ServiceProvider sp LEFT JOIN FETCH sp.reviews")
    List<ServiceProvider> findAllServiceProvidersWithReviews();

    @Query("SELECT sp, r FROM ServiceProvider sp LEFT JOIN sp.reviews r WHERE sp.serviceProviderId = :serviceProviderId")
    ServiceProvider findOneWithReviews(@Param("serviceProviderId") Long serviceProviderId);

    @Query("SELECT sp FROM ServiceProvider sp")
    List<ServiceProvider> findAllServiceProviders();

     ServiceProvider findByServiceProviderId(Long serviceProviderId);
}
