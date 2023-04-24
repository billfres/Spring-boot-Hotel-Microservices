package com.fres.hotelservice.services.impl;


import com.fres.hotelservice.exceptions.ResourceNotFoundException;
import com.fres.hotelservice.models.Hotel;
import com.fres.hotelservice.repositories.HotelRepository;
import com.fres.hotelservice.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String randomHotelId = UUID.randomUUID().toString();
        hotel.setHotelId(randomHotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {

        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {

        return hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id is not found on server :) !"));
    }
}
