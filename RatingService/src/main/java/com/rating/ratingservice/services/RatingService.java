package com.rating.ratingservice.services;

import com.rating.ratingservice.models.Rating;

import java.util.List;

public interface RatingService {

    Rating Create(Rating rating);
    List<Rating> getAllRatings();
    Rating getRating(String ratingId);

    List<Rating> getRatingByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotleId);
}
