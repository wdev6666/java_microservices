package com.microservice.RatingService.services;

import com.microservice.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {
    public Rating addRating(Rating rating);

    public List<Rating> getRatings();

    public Rating getRating(int ratingId);

    public Rating updateRating(int ratingId, Rating rating);

    public boolean deleteRating(int ratingId);
}
