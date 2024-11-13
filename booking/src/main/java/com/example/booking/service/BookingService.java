package com.example.booking.service;

import com.example.booking.entity.Booking;
import com.example.booking.entity.Hotel;

import java.util.List;

public interface BookingService {
    List<Booking> findAll();

    Booking findById(Long id);

    Booking save(Booking booking);

    Booking update(Booking booking);

    void deleteById(Long id);
}
