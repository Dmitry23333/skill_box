package com.example.spring_jwt_auth_example.web.model.request.post;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpsertPostRequest {
    @NotNull(message = "page size must be specified!")
    @Positive(message = "category id must be greater than 0!")
    private Long categoryId;
    @NotBlank(message = "title must be specified!")
    @Size(min = 3, max = 10, message = "title cannot be less than 3 and more than 10 characters!")
    private String title;
    @NotBlank(message = "description must be specified!")
    @Size(min = 3, max = 20, message = "description cannot be less than 3 and more than 20 characters!")
    private String description;
    @NotBlank(message = "body must be specified!")
    @Size(min = 3, max = 100, message = "body cannot be less than 3 and more than 100 characters!")
    private String body;
}
