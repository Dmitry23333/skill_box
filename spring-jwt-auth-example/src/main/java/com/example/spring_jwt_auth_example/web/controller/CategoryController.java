package com.example.spring_jwt_auth_example.web.controller;


import com.example.spring_jwt_auth_example.entity.Category;
import com.example.spring_jwt_auth_example.mapper.CategoryMapper;
import com.example.spring_jwt_auth_example.service.CategoryService;
import com.example.spring_jwt_auth_example.web.PaginationRequest;
import com.example.spring_jwt_auth_example.web.model.request.category.UpsertCategoryRequest;
import com.example.spring_jwt_auth_example.web.model.response.category.CategoryListResponse;
import com.example.spring_jwt_auth_example.web.model.response.category.CategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<CategoryListResponse> findAll(@Valid PaginationRequest request) {
        return ResponseEntity.ok(
                categoryMapper.categoryToCategoryResponseList(
                        categoryService.findAll(request))
        );
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                categoryMapper.categoryToResponse(
                        categoryService.findById(id)
                )
        );
    }
    @PostMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid UpsertCategoryRequest request) {
        Category newCategory = categoryService.save(categoryMapper.requestToCategory(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryMapper.categoryToResponse(newCategory));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<CategoryResponse> update(@PathVariable("id") Long categoryId, @RequestBody @Valid UpsertCategoryRequest request) {

        Category updatedCategory = categoryService.update(categoryMapper.requestToCategory(categoryId, request));

        return ResponseEntity.ok(categoryMapper.categoryToResponse(updatedCategory));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
