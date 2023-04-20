package com.microservice.HotelService.services;

import com.microservice.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {
    public Hotel saveHotel(Hotel hotel);

    public List<Hotel> getAllHotels();

    public Hotel getById(String hotelId);

    public boolean deleteHotel(String hotelId);

    public Hotel updateHotel(Hotel hotel, String hotelId);
}
