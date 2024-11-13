package com.example.booking.dto.unavaialbledate;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UnavailableDateResponse {
    private LocalDate date;
}
