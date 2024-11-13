package com.example.booking.controller;

import com.example.booking.dto.hotel.HotelFilterRequest;
import com.example.booking.dto.hotel.HotelListResponse;
import com.example.booking.dto.hotel.HotelResponse;
import com.example.booking.dto.hotel.UpsertHotelRequest;
import com.example.booking.entity.Hotel;
import com.example.booking.mapper.HotelMapper;
import com.example.booking.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking/hotel")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;
    private final HotelMapper hotelMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Validated
    public ResponseEntity<HotelListResponse> findAll(@Valid HotelFilterRequest request){
        return ResponseEntity.ok(
                hotelMapper.hotelToHotelResponseList(
                        hotelService.filterBy(request)
                )
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<HotelResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                hotelMapper.hotelToResponse(
                        hotelService.findById(id)
                )
        );
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HotelResponse> create(@RequestBody UpsertHotelRequest request){
        Hotel newHotel = hotelService.save(hotelMapper.RequestToHotel(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hotelMapper.hotelToResponse(newHotel));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HotelResponse> update(@PathVariable("id") Long id, @RequestBody UpsertHotelRequest request){
        Hotel updatedHotel = hotelService.update(hotelMapper.RequestToHotel(id,request));
        return ResponseEntity.ok(hotelMapper.hotelToResponse(updatedHotel));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        hotelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/rating/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<Void> updateRating(@RequestParam  Integer mark, @PathVariable Long id){
        hotelService.updateRating(id,mark);
        return ResponseEntity.noContent().build();
    }
}
