package com.example.service.integrationapp.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertBookRequest {
    private String author;
    private String title;
    private String category_name;
}




