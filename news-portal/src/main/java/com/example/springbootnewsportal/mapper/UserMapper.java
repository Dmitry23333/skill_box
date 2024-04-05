package com.example.springbootnewsportal.mapper;

import com.example.springbootnewsportal.entity.User;
import com.example.springbootnewsportal.web.model.request.user.UpsertUserRequest;
import com.example.springbootnewsportal.web.model.response.user.UserListResponse;
import com.example.springbootnewsportal.web.model.response.user.UserResponse;
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

    User RequestToUser(UpsertUserRequest request);

    @Mapping(source = "userId", target = "id")
    User RequestToUser(Long userId, UpsertUserRequest request);

    UserResponse userToResponse(User user);

    default UserListResponse userToUserResponseList(List<User> users) {
        UserListResponse response = new UserListResponse();
        response.setUsers(users.stream()
                .map(this::userToResponse).collect(Collectors.toList()));
        return response;
    }

}
