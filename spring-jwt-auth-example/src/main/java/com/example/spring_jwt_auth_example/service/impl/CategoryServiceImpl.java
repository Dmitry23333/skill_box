package com.example.spring_jwt_auth_example.service.impl;


import com.example.spring_jwt_auth_example.entity.Category;
import com.example.spring_jwt_auth_example.repository.CategoryRepository;
import com.example.spring_jwt_auth_example.service.CategoryService;
import com.example.spring_jwt_auth_example.utils.BeanUtils;
import com.example.spring_jwt_auth_example.web.PaginationRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll(PaginationRequest request) {
        return categoryRepository.findAll(request.pageRequest()).getContent();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("?????? ? ID {0} ?? ??????!", id)));
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        Category existedCategory = findById(category.getId());
        BeanUtils.copyNonNullProperties(category, existedCategory);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
