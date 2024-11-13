package com.example.booking.dto.booking;

import com.example.booking.dto.room.BriefRoomResponse;
import com.example.booking.dto.room.RoomResponse;
import com.example.booking.dto.user.UserResponse;
import com.example.booking.entity.Room;
import com.example.booking.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingResponse {

    private Long id;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private BriefRoomResponse room;
    private UserResponse user;
}
