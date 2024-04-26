package com.example.service.integration.repository;

import com.example.service.integration.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    boolean existsCategoryByName(String name);

    Category findCategoryByName(String name);

}
