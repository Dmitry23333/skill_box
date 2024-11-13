package com.example.booking.dto.room;

import com.example.booking.dto.hotel.BriefHotelResponse;
import com.example.booking.dto.hotel.HotelResponse;
import lombok.Data;

@Data
public class BriefRoomResponse {
    private Long id;
    private String title;
    private Integer number;
    private Double price;
    private Integer capacity;
}
