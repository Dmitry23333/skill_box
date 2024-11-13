package com.example.booking.dto.hotel;

import lombok.Data;

@Data
public class BriefHotelResponse {
    private Long id;
    private String name;
    private String title;
    private String city;
    private String address;
}
