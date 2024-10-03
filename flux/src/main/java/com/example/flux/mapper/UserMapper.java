package com.example.flux.mapper;

import com.example.flux.entity.User;
import com.example.flux.web.requset.UpsertUserRequest;
import com.example.flux.web.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {
    User requestToUser(UpsertUserRequest request);
    UserResponse userToResponse(User user);
}
