package com.example.springbootnewsportal.repository;

import com.example.springbootnewsportal.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
