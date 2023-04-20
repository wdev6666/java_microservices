package com.microservice.RatingService.services.impl;

import com.microservice.RatingService.entities.Rating;
import com.microservice.RatingService.exceptions.ResourceNotFoundException;
import com.microservice.RatingService.repositories.RatingRepository;
import com.microservice.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating addRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRating(int ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating not found with ratingId: "+ ratingId));
    }

    @Override
    public Rating updateRating(int ratingId, Rating rating) {
        Rating rating1 = ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating not found with ratingId: "+ ratingId));
        rating1.setRating(rating.getRating() != 0 ? rating.getRating() : rating1.getRating());
        return ratingRepository.save(rating);
    }

    @Override
    public boolean deleteRating(int ratingId) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating not found with ratingId: "+ ratingId));
        ratingRepository.delete(rating);
        return true;
    }
}
