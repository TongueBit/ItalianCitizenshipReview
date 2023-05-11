package com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

}
