package com.example.springbootnewsportal.web.model.request.user;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertUserRequest {
    @NotBlank(message = "The user name must be entered")
    @Size(min = 3, max = 30, message = "client name cannot be less than 3 and more than 30 characters!")
    private String username;

}
