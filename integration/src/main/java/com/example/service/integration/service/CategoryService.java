package com.example.service.integration.service;

import com.example.service.integration.entity.Category;

public interface CategoryService {

    void save(Category category);

    boolean existsCategoryByName(String name);

    Category findByName(String name);


}
