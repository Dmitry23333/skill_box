package com.example.booking.controller;

import com.example.booking.dto.booking.BookingListResponse;
import com.example.booking.dto.booking.BookingResponse;
import com.example.booking.dto.booking.UpsertBookingRequest;
import com.example.booking.entity.Booking;
import com.example.booking.mapper.BookingMapper;
import com.example.booking.repository.BookingRepository;
import com.example.booking.service.BookingService;
import com.example.booking.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper mapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<BookingResponse> create(@RequestBody UpsertBookingRequest request) {
        Booking booking = bookingService.save(mapper.requestToBooking(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.bookingToResponse(booking));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Validated
    public ResponseEntity<BookingListResponse> findAll(){
        return ResponseEntity.ok(
                mapper.booingToBookingResponseList(
                        bookingService.findAll()
                )
        );
    }
}
