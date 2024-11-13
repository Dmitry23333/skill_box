package com.example.booking.service;

import com.example.booking.dto.hotel.HotelFilterRequest;
import com.example.booking.entity.Hotel;

import java.util.List;

public interface HotelService {
    List <Hotel> filterBy(HotelFilterRequest filter);
    //List<Hotel> findAll(PaginationRequest request);

    Hotel findById(Long id);

    Hotel save(Hotel hotel);

    Hotel update(Hotel hotel);

    void deleteById(Long id);

    void updateRating(Long hotelId, Integer newMark);

}
