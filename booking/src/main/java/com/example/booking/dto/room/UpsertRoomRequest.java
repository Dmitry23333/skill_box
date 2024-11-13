package com.example.booking.dto.room;


import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UpsertRoomRequest {
    private Long id;
    private String title;
    private Integer number;
    private Double price;
    private Integer capacity;
    private Long hotelId;
}
