package com.fres.userservice.services.impl;


import com.fres.userservice.exceptions.ResourceNotFoundException;
import com.fres.userservice.external.services.HotelService;
import com.fres.userservice.models.Hotel;
import com.fres.userservice.models.Rating;
import com.fres.userservice.models.User;
import com.fres.userservice.repositories.UserRepository;
import com.fres.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

         User user =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server :) !"));
        //fetch rating of the above user from RATING SERVICE
        //http://localhost:8083/user_ratings/users/2ebb593c-edf3-4d05-9cb9-0a85a3bc7f58

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{} ", ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hoteL service to fet the hotel
            //http://localhost:8082/hotels/e32bbd40-181a-4177-9d11-7ff40a419e6f

            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //logger.info("response status code: {} ", forEntity.getStatusCode());

            //Set hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return  rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return  user;
    }
}
