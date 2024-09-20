package com.example.spring_jwt_auth_example.web;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationRequest {
    @NotNull(message = "page size must be specified!")
    @Positive(message = "page size must be greater than 0!")
    private Integer pageSize;
    @NotNull(message = "page number must be specified!")
    @PositiveOrZero(message = "page size must be 0 or greater than 0!")
    private Integer pageNumber;
    public PageRequest pageRequest() {
        return PageRequest.of(pageNumber, pageSize);
    }

}
