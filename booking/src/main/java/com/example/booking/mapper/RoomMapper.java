package com.example.booking.mapper;

import com.example.booking.dto.hotel.BriefHotelResponse;
import com.example.booking.dto.hotel.HotelListResponse;
import com.example.booking.dto.room.BriefRoomResponse;
import com.example.booking.dto.room.RoomListResponse;
import com.example.booking.dto.room.RoomResponse;
import com.example.booking.dto.room.UpsertRoomRequest;
import com.example.booking.entity.Hotel;
import com.example.booking.entity.Room;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;


@DecoratedWith(RoomMapperDelegate.class)
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RoomMapper {
    Room requestToRoom(UpsertRoomRequest request);

    @Mapping(source = "roomId", target = "id")
    Room requestToRoom(Long roomId, UpsertRoomRequest request);

    RoomResponse roomToResponse(Room room);

    BriefRoomResponse roomToBriefResponse(Room room);

    default RoomListResponse roomToRoomResponseList(List<Room> rooms){
        RoomListResponse response = new RoomListResponse();
        response.setRooms(rooms.stream()
                .map(this::roomToResponse).collect(Collectors.toList()));
        return response;
    }
}
