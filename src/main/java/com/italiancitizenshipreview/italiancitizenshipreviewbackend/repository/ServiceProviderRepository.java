package com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import org.springframework.data.repository.CrudRepository;

import java.security.Provider;

public interface ServiceProviderRepository extends CrudRepository<ServiceProvider, Long> {

}
