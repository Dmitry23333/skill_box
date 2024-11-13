package com.example.booking.service;

import com.example.booking.entity.Hotel;
import com.example.booking.entity.Room;
import com.example.booking.entity.UnavailableDate;

import java.time.LocalDate;
import java.util.List;

public interface UnavailableDateService {
    boolean existsByRoomIdAndDate(Long roomId, LocalDate date);

    List<UnavailableDate> findAll();
    UnavailableDate save(UnavailableDate unavailableDate);

    UnavailableDate update(UnavailableDate unavailableDate);

    void deleteById(Long id);
}
