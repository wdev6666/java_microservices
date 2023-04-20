package com.microservice.HotelService.services.services.impl;

import com.microservice.HotelService.entities.Hotel;
import com.microservice.HotelService.exceptions.ResourceNotFoundException;
import com.microservice.HotelService.repositories.HotelRepository;
import com.microservice.HotelService.services.HotelService;
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
    public Hotel getById(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with hotelId: " + hotelId));
    }

    @Override
    public boolean deleteHotel(String hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with hotelId: "+hotelId));
        hotelRepository.delete(hotel);
        return true;
    }

    @Override
    public Hotel updateHotel(Hotel hotel, String hotelId) {
        Hotel hotel1 = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with hotelId: "+hotelId));
        hotel1.setName(hotel.getName() != "" ? hotel.getName():hotel1.getName());
        return hotelRepository.save(hotel1);
    }
}
