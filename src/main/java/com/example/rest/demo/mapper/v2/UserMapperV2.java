package com.example.rest.demo.mapper.v2;


import com.example.rest.demo.model.User;
import com.example.rest.demo.web.model.UpsertUserRequest;
import com.example.rest.demo.web.model.UserListResponse;
import com.example.rest.demo.web.model.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, uses = {NewsMapperV2.class})
public interface UserMapperV2 {

    User requestToUser(UpsertUserRequest request);

    @Mapping(target = "id", source = "userId")
    User requestToUser (Long userId, UpsertUserRequest request);

    UserResponse userToResponse(User user);

    default UserListResponse userToUserResponseList(List <User> users){
        UserListResponse response = new UserListResponse();
        response.setUsers(users.stream()
                .map(this::userToResponse).collect(Collectors.toList()));

        return response;
    }
}
