package com.fres.hotelservice.services;

import com.fres.hotelservice.models.Hotel;

import java.util.List;

public interface HotelService {

    Hotel saveHotel(Hotel hotel);
    List<Hotel> getAllHotels();
    Hotel getHotel(String hotelId);
}
