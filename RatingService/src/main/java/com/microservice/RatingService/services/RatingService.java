package com.microservice.RatingService.services;

import com.microservice.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {
    public Rating addRating(Rating rating);

    public List<Rating> getRatings();

    public List<Rating> getRatingsByUserId(String userId);

    public List<Rating> getRatingsByHotelId(String userId);

    public Rating getRating(String ratingId);

    public Rating updateRating(String ratingId, Rating rating);

    public boolean deleteRating(String ratingId);
}
