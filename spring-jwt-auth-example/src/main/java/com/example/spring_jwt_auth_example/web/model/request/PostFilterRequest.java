package com.example.spring_jwt_auth_example.web.model.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostFilterRequest {
    @NotNull(message = "page size must be specified!")
    @Positive(message = "page size must be greater than 0!")
    private Integer pageSize;
    @NotNull(message = "page number must be specified!")
    @PositiveOrZero(message = "page size must be 0 or greater than 0!")
    private Integer pageNumber;
    @Positive(message = "category id must be greater than 0!")
    private Long categoryId;
    @Positive(message = "author id must be greater than 0!")
    private Long authorId;

}
