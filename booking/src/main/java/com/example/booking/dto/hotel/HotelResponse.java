package com.example.booking.dto.hotel;

import com.example.booking.dto.room.BriefRoomResponse;
import com.example.booking.dto.room.RoomResponse;
import com.example.booking.entity.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HotelResponse {
    private Long id;
    private String name;
    private String title;
    private String city;
    private String address;
    private Integer distanceToCentre;
    private Double rating;
    private Integer numberOfRating;
    private List<BriefRoomResponse> roomList = new ArrayList<>();
}
