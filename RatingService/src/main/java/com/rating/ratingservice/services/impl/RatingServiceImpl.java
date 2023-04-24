package com.rating.ratingservice.services.impl;


import com.rating.ratingservice.exceptions.ResourceNotFoundException;
import com.rating.ratingservice.models.Rating;
import com.rating.ratingservice.repositories.RatingRepository;
import com.rating.ratingservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
/*
    @Autowired
    private RestTemplate restTemplate;*/

    @Override
    public Rating Create(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {

        return ratingRepository.findAll();
    }

    @Override
    public Rating getRating(String ratingId) {

        return ratingRepository.findById(ratingId).orElseThrow(()-> new ResourceNotFoundException("Rating with given id is not found on server :) !"));
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotleId) {
        return ratingRepository.findByHotelId(hotleId);
    }
}
