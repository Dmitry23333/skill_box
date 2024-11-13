package com.example.booking.dto.room;

import com.example.booking.entity.Hotel;
import com.example.booking.entity.UnavailableDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class RoomFilterRequest {
    @NotNull(message = "page size must be specified!")
    @Positive(message = "page size must be greater than 0!")
    private Integer pageSize;

    @NotNull(message = "page number must be specified!")
    @PositiveOrZero(message = "page size must be 0 or greater than 0!")
    private Integer pageNumber;

    @Positive(message = "category id must be greater than 0!")
    private Long id;

    private String title;
    @PositiveOrZero(message = "page size must be 0 or greater than 0!")
    private Double minPrice;
    @Positive(message = "page size must be greater than 0!")
    private Double maxPrice;
    private Integer capacity;
    private Long hotelId;

    private LocalDate arrivalDate;
    private LocalDate departureDate;

}
