package com.example.booking.controller;


import com.example.booking.dto.hotel.HotelFilterRequest;
import com.example.booking.dto.hotel.HotelListResponse;
import com.example.booking.dto.room.RoomFilterRequest;
import com.example.booking.dto.room.RoomListResponse;
import com.example.booking.dto.room.RoomResponse;
import com.example.booking.dto.room.UpsertRoomRequest;
import com.example.booking.entity.Room;
import com.example.booking.mapper.RoomMapper;
import com.example.booking.service.RoomService;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/api/booking/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final RoomMapper roomMapper;


    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Validated
    public ResponseEntity<RoomListResponse> findAll(@Valid RoomFilterRequest request){
        return ResponseEntity.ok(
                roomMapper.roomToRoomResponseList(
                        roomService.filterBy(request)
                )
        );
    }

    @GetMapping("/d")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Validated
    public ResponseEntity<RoomListResponse> findAlll(){
        return ResponseEntity.ok(
                roomMapper.roomToRoomResponseList(
                        roomService.findAll()
                )
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<RoomResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                roomMapper.roomToResponse(
                        roomService.findById(id)
                )
        );
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RoomResponse> create(@RequestBody UpsertRoomRequest request){
        Room newRoom = roomService.save(roomMapper.requestToRoom(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomMapper.roomToResponse(newRoom));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RoomResponse> update(@PathVariable("id") Long id, @RequestBody UpsertRoomRequest request){
        Room updatedRoom = roomService.update(roomMapper.requestToRoom(id,request));
        return ResponseEntity.ok(roomMapper.roomToResponse(updatedRoom));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        roomService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
