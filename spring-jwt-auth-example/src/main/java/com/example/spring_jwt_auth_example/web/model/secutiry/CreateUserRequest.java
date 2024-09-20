package com.example.spring_jwt_auth_example.web.model.secutiry;

import com.example.spring_jwt_auth_example.entity.RoleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {
    @NotBlank(message = "The user name must be entered")
    @Size(min = 3, max = 30, message = "client name cannot be less than 3 and more than 30 characters!")
    private String username;
    @NotBlank(message = "email must be entered")
    private String email;
    @NotBlank(message = "role type must be entered")
    private Set<RoleType> roles;
    @NotBlank(message = "The password must be entered")
    private String password;
}
