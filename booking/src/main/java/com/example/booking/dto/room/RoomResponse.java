package com.example.booking.dto.room;

import com.example.booking.dto.hotel.BriefHotelResponse;
import com.example.booking.dto.hotel.HotelResponse;
import com.example.booking.dto.unavaialbledate.UnavailableDateResponse;
import com.example.booking.entity.UnavailableDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomResponse {
    private Long id;
    private String title;
    private Integer number;
    private Double price;
    private Integer capacity;
    private List<UnavailableDateResponse> unavailableDates;
    private BriefHotelResponse hotel;
}
