package com.example.spring_jwt_auth_example.repository;


import com.example.spring_jwt_auth_example.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
