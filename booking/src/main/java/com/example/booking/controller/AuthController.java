package com.example.booking.controller;

import com.example.booking.dto.security.LoginRequest;
import com.example.booking.dto.security.SimpleResponse;
import com.example.booking.dto.user.UpsertUserRequest;
import com.example.booking.dto.user.UserResponse;
import com.example.booking.repository.UserRepository;
import com.example.booking.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/booking/auth")
@RequiredArgsConstructor
public class AuthController {
    private final SecurityService service;

    @PostMapping("/register")
    public ResponseEntity<SimpleResponse> registerUser(@RequestBody UpsertUserRequest createUserRequest){
        service.register(createUserRequest);
        return ResponseEntity.ok(new SimpleResponse("User created"));
    }
}
