package com.example.booking.dto.user;

import com.example.booking.entity.UserRole;
import lombok.Data;

@Data
public class UpsertUserRequest {
    private String name;
    private String password;
    private String email;
    private UserRole userRole;
}
