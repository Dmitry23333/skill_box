package com.example.booking.service;

import com.example.booking.dto.room.RoomFilterRequest;
import com.example.booking.entity.Room;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RoomService {

    List <Room> filterBy(RoomFilterRequest filter);
    List<Room> findAll();

    Room findById(Long id);

    Room save(Room room);

    Room update(Room room);

    void deleteById(Long id);

}
