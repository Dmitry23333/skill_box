package com.example.spring_jwt_auth_example.mapper;


import com.example.spring_jwt_auth_example.entity.User;
import com.example.spring_jwt_auth_example.web.model.response.user.UserListResponse;
import com.example.spring_jwt_auth_example.web.model.response.user.UserResponse;
import com.example.spring_jwt_auth_example.web.model.secutiry.CreateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {
    User RequestToUser(CreateUserRequest request);
    @Mapping(source = "userId", target = "id")
    User RequestToUser(Long userId, CreateUserRequest request);
    UserResponse userToResponse(User user);
    default UserListResponse userToUserResponseList(List<User> users) {
        UserListResponse response = new UserListResponse();
        response.setUsers(users.stream()
                .map(this::userToResponse).collect(Collectors.toList()));
        return response;
    }
}
