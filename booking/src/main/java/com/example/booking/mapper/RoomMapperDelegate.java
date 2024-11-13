package com.example.booking.mapper;

import com.example.booking.dto.room.BriefRoomResponse;
import com.example.booking.dto.room.UpsertRoomRequest;
import com.example.booking.entity.Room;
import com.example.booking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;



public abstract class RoomMapperDelegate implements RoomMapper {
    @Autowired
    private HotelService hotelService;

    private HotelMapper mapper;

    @Override
    public Room requestToRoom(UpsertRoomRequest request) {
        Room room = new Room();
        room.setTitle(request.getTitle());
        room.setNumber(request.getNumber());
        room.setCapacity(request.getCapacity());
        room.setPrice(request.getPrice());
        room.setHotel(hotelService.findById(request.getHotelId()));
        return room;
    }

    @Override
    public Room requestToRoom(Long roomId, UpsertRoomRequest request) {
        Room room = requestToRoom(request);
        room.setId(roomId);
        return room;
    }


}
