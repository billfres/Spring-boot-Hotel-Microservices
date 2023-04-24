package com.rating.ratingservice.controller;

import com.rating.ratingservice.models.Rating;
import com.rating.ratingservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating rating1 = ratingService.Create(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getSingleUser(@PathVariable String ratingId){
        Rating rating =ratingService.getRating(ratingId);
        return ResponseEntity.ok(rating);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
        List<Rating> allRatings = ratingService.getAllRatings();
        return ResponseEntity.ok(allRatings);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingsByUserId(@PathVariable String userId){
        List<Rating> allRatings = ratingService.getRatingByUserId(userId);
        return ResponseEntity.ok(allRatings);
    }

    @GetMapping("/hotels/{hoteleId}")
    public ResponseEntity<List<Rating>> getAllRatingsByHotelId(@PathVariable String hoteleId){
        List<Rating> allRatings = ratingService.getRatingByHotelId(hoteleId);
        return ResponseEntity.ok(allRatings);
    }
}
