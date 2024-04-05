package com.example.springbootnewsportal.web.model.response.category;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryListResponse {
    List<CategoryResponse> categories = new ArrayList<>();
}
