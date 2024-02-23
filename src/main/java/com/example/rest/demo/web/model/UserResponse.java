package com.example.rest.demo.web.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponse {
    private Long id;

    private String name;

    private List<NewsResponse> orders = new ArrayList<>();
}
