package com.example.booking.dto.user;

import com.example.booking.entity.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private UserRole userRole;
}
