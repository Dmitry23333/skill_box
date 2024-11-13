package com.example.booking.dto.hotel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UpsertHotelRequest {
    private String name;
    private String title;
    private String city;
    private String address;
    private Integer distanceToCentre;
}
