package com.example.spring_jwt_auth_example.web.model.request.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpsertCommentRequest {
    @NotNull(message = "page size must be specified!")
    @Positive(message = "category id must be greater than 0!")
    private Long postId;
    @NotBlank(message = "description must be specified!")
    @Size(min = 3, max = 20, message = "description cannot be less than 3 and more than 20 characters!")
    private String comment;
}
