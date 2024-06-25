package com.example.service.integration.service.impl;

import com.example.service.integration.entity.Category;
import com.example.service.integration.repository.CategoryRepository;
import com.example.service.integration.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public boolean existsCategoryByName(String name) {
        return categoryRepository.existsCategoryByName(name);
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }
}
