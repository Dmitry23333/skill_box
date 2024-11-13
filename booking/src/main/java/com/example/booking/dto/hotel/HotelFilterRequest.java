package com.example.booking.dto.hotel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HotelFilterRequest {
    @NotNull(message = "page size must be specified!")
    @Positive(message = "page size must be greater than 0!")
    private Integer pageSize;

    @NotNull(message = "page number must be specified!")
    @PositiveOrZero(message = "page size must be 0 or greater than 0!")
    private Integer pageNumber;

    @Positive(message = "category id must be greater than 0!")
    private Long id;

    private String name;

    private String title;

    private String city;

    private String address;
    @Positive(message = "distanceToCentre must be greater than 0!")
    private Integer distanceToCentre;

    @Positive(message = "distanceToCentre must be greater than 0!")
    private Double rating;
    @Positive(message = "distanceToCentre must be greater than 0!")
    private Integer numberOfRating;
}
