package com.example.rest.demo.mapper.v1;

import com.example.rest.demo.model.User;
import com.example.rest.demo.web.model.UpsertUserRequest;
import com.example.rest.demo.web.model.UserListResponse;
import com.example.rest.demo.web.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final NewsMapper newsMapper;

    public User requestToUser(UpsertUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        return user;
    }

    public User requestToUser(Long userId, UpsertUserRequest request) {
        User user = requestToUser(request);
        user.setId(userId);
        return user;
    }

    public UserResponse userToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setOrders(newsMapper.newsListToResponseList(user.getNewsList()));
        return userResponse;
    }

    public UserListResponse userListToUserResponseList(List<User> users){
        UserListResponse response = new UserListResponse();
        response.setUsers(users.stream().map(this::userToResponse).collect(Collectors.toList()));
        return response;
    }
}
