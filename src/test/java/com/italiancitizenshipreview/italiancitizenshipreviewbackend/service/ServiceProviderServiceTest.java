package com.italiancitizenshipreview.italiancitizenshipreviewbackend.service;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.ServiceProviderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ServiceProviderServiceTest {


    @Mock
    private ServiceProviderService serviceProviderService;



    @Mock
    private ServiceProviderRepository serviceProviderRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllServiceProviders() {
        //Arrange
        List<ServiceProvider> serviceProviders = new ArrayList<ServiceProvider>();
        serviceProviders.add(new ServiceProvider());
        serviceProviders.add(new ServiceProvider());
        serviceProviders.add(new ServiceProvider());
        when(serviceProviderService.getAllServiceProviders()).thenReturn(serviceProviders);
        assertEquals(3, serviceProviderService.getAllServiceProviders().size());
    }

    @Test
    public void testCreateServiceProvider() {
        // Arrange
        String name = "Test Name";
        String description = "Test Description";
        ServiceProvider expectedServiceProvider = new ServiceProvider();
        expectedServiceProvider.setName(name);
        expectedServiceProvider.setDescription(description);

        when(serviceProviderService.createServiceProvider(name, description)).thenReturn(expectedServiceProvider);

        // Act
        ServiceProvider result = serviceProviderService.createServiceProvider(name, description);

        // Assert
        assertNotNull(result);
        assertEquals(expectedServiceProvider.getName(), result.getName());
        assertEquals(expectedServiceProvider.getDescription(), result.getDescription());
    }


    @Test
    public void testGetServiceProvider() {
        // Arrange
        Long serviceProviderId = 1L;
        ServiceProvider expectedServiceProvider = new ServiceProvider();
        expectedServiceProvider.setServiceProviderId(serviceProviderId);
        when(serviceProviderService.getServiceProvider(serviceProviderId)).thenReturn(expectedServiceProvider);

        // Act
        ServiceProvider result = serviceProviderService.getServiceProvider(serviceProviderId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedServiceProvider.getServiceProviderId(), result.getServiceProviderId());
    }

    @Test //Not confident this does anything at all
    public void testGetOneServiceProviderWithReviews() throws Exception {
        // Arrange
        Long serviceProviderId = 1L;
        ServiceProvider expectedServiceProvider = new ServiceProvider();
        expectedServiceProvider.setServiceProviderId(serviceProviderId);
        expectedServiceProvider.setName("Test Provider");

        List<Review> reviews = new ArrayList<Review>();
        Review review1 = new Review();
        review1.setReviewId(1L);
        review1.setContent("Review 1");
        Review review2 = new Review();
        review2.setReviewId(2L);
        review2.setContent("Review 2");
        reviews.add(review1);
        reviews.add(review2);

        expectedServiceProvider.setReviews(reviews);

        // Mock the repository
        ServiceProviderRepository serviceProviderRepository = Mockito.mock(ServiceProviderRepository.class);

        // Set up the mock behavior
        when(serviceProviderRepository.findOneWithReviews(serviceProviderId))
                .thenReturn(expectedServiceProvider);

        // Create the ServiceProviderService instance
        ServiceProviderService serviceProviderService = new ServiceProviderService();

        // Use reflection to set the mock repository into the private field
        Field field = serviceProviderService.getClass().getDeclaredField("serviceProviderRepository");
        field.setAccessible(true);
        field.set(serviceProviderService, serviceProviderRepository);

        // Act
        ServiceProvider actualServiceProvider = serviceProviderService.getOneServiceProviderWithReviews(serviceProviderId);

        // Assert
        assertEquals(expectedServiceProvider, actualServiceProvider);
        assertEquals(expectedServiceProvider.getReviews(), actualServiceProvider.getReviews());
    }

    @Test
    public void testUpdateServiceProvider() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setServiceProviderId(1L);
        serviceProvider.setName("Test Provider");

        // Create a mock of the repository
        ServiceProviderRepository serviceProviderRepository = Mockito.mock(ServiceProviderRepository.class);

        // Create an instance of the service and set the mock repository
        ServiceProviderService serviceProviderService = new ServiceProviderService();

        // Mock the repository

        // Use reflection to set the mock repository into the private field
        Field field = serviceProviderService.getClass().getDeclaredField("serviceProviderRepository");
        field.setAccessible(true);
        field.set(serviceProviderService, serviceProviderRepository);

        // Act
        serviceProviderService.updateServiceProvider(serviceProvider);

        // Assert
        // Verify that the save method of the mock repository is called with the correct serviceProvider object
        Mockito.verify(serviceProviderRepository).save(serviceProvider);
    }
/*
    @Test //not sure how to test this
    public List<ServiceProvider> testGetAllServiceProviderswithoutReviews() {
        return serviceProviderRepository.findAllServiceProviders();
    }
*/
}

