package com.example.booking.mapper;

import com.example.booking.dto.room.RoomResponse;
import com.example.booking.dto.room.UpsertRoomRequest;
import com.example.booking.dto.user.UpsertUserRequest;
import com.example.booking.dto.user.UserResponse;
import com.example.booking.entity.Room;
import com.example.booking.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {
    User requestToUser(UpsertUserRequest request);

    @Mapping(source = "userId", target = "id")
    User requestToUser(Long userId, UpsertUserRequest request);

    UserResponse userToResponse(User user);
}
