package com.example.spring_jwt_auth_example.mapper;


import com.example.spring_jwt_auth_example.entity.Category;
import com.example.spring_jwt_auth_example.web.model.request.category.UpsertCategoryRequest;
import com.example.spring_jwt_auth_example.web.model.response.category.CategoryListResponse;
import com.example.spring_jwt_auth_example.web.model.response.category.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoryMapper {
    Category requestToCategory(UpsertCategoryRequest request);
    @Mapping(source = "categoryId", target = "id")
    Category requestToCategory(Long categoryId, UpsertCategoryRequest request);
    CategoryResponse categoryToResponse(Category category);
    default CategoryListResponse categoryToCategoryResponseList(List<Category> categories) {
        CategoryListResponse response = new CategoryListResponse();
        response.setCategories(categories.stream()
                .map(this::categoryToResponse).collect(Collectors.toList()));
        return response;
    }
}
