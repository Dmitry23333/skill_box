package com.example.booking.security;


import com.example.booking.dto.user.UpsertUserRequest;
import com.example.booking.entity.User;
import com.example.booking.exception.AlreadyExistsException;
import com.example.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public void register(UpsertUserRequest createUserRequest) {
        var user = User.builder()
                .name(createUserRequest.getName())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .email(createUserRequest.getEmail())
                .build();
        user.setUserRole(createUserRequest.getUserRole());
        if (!userService.existsUserByName(user.getName()) && !userService.existsUserByEmail(user.getEmail())) {
            userService.save(user);
        } else {
            throw new AlreadyExistsException(
                    MessageFormat.format("Username {0} or email {1} exist", user.getName(), user.getEmail()));
        }
    }
}
