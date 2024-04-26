package com.example.service.integration.mapper;

import com.example.service.integration.entity.Category;
import com.example.service.integration.web.model.request.UpsertBookRequest;
import org.springframework.stereotype.Component;


@Component
public class CategoryMapper {


    Category requestToCategory(UpsertBookRequest request) {
        Category category = new Category();
        category.setName(request.getCategoryName());
        return category;
    }
}
