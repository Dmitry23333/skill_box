package com.example.service.integrationapp.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private UUID id;

    private String author;

    private String title;

    private CategoryResponse category;
}
