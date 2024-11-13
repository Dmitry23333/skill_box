package com.example.booking.mapper;

import com.example.booking.dto.booking.UpsertBookingRequest;
import com.example.booking.entity.Booking;
import com.example.booking.service.RoomService;
import com.example.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

public abstract class BookingMapperDelegate implements BookingMapper {
    @Autowired
    private  RoomService roomService;

    @Autowired
    private UserService userService;
    @Override
    public Booking requestToBooking(UpsertBookingRequest request) {
        Booking booking = new Booking();
        booking.setArrivalDate(request.getArrivalDate());
        booking.setDepartureDate(request.getDepartureDate());
        booking.setUser(userService.findById(request.getUserId()));
        booking.setRoom(roomService.findById(request.getRoomId()));
        return booking;
    }
}
