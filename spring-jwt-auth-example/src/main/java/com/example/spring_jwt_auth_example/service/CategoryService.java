package com.example.spring_jwt_auth_example.service;



import com.example.spring_jwt_auth_example.entity.Category;
import com.example.spring_jwt_auth_example.web.PaginationRequest;

import java.util.List;

public interface CategoryService {
    List<Category> findAll(PaginationRequest request);
    Category findById(Long id);
    Category save(Category category);
    Category update(Category category);
    void deleteById(Long id);

}
