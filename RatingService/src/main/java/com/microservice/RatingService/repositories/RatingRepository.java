package com.microservice.RatingService.repositories;

import com.microservice.RatingService.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepository extends MongoRepository<Rating, Integer> {
}
