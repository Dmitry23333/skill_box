package com.example.springbootnewsportal.service;

import com.example.springbootnewsportal.entity.Category;
import com.example.springbootnewsportal.web.PaginationRequest;


import java.util.List;

public interface CategoryService {
    List<Category> findAll(PaginationRequest request);

    Category findById(Long id);

    Category save(Category category);

    Category update(Category category);

    void deleteById(Long id);

}
