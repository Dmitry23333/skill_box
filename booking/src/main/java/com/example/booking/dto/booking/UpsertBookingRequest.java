package com.example.booking.dto.booking;


import lombok.Data;


import java.time.LocalDate;


@Data
public class UpsertBookingRequest {
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private Long roomId;
    private Long userId;
}
