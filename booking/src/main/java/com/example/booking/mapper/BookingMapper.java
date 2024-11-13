package com.example.booking.mapper;

import com.example.booking.dto.booking.BookingListResponse;
import com.example.booking.dto.booking.BookingResponse;
import com.example.booking.dto.booking.UpsertBookingRequest;
import com.example.booking.entity.Booking;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@DecoratedWith(BookingMapperDelegate.class)
public interface BookingMapper {
    Booking requestToBooking(UpsertBookingRequest request);

    @Mapping(source = "bookingId", target = "id")
    Booking requestToBooking(Long bookingId, UpsertBookingRequest request);

    BookingResponse bookingToResponse(Booking booking);
    default BookingListResponse booingToBookingResponseList(List<Booking> bookings){
        BookingListResponse response = new BookingListResponse();
        response.setBookings(bookings.stream()
                .map(this::bookingToResponse).collect(Collectors.toList()));
        return response;
    }
}
