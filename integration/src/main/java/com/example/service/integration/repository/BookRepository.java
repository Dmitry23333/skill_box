package com.example.service.integration.repository;

import com.example.service.integration.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findAllByCategory_Name(String name);

    Book findFirstByAuthorAndTitle(String author, String title);
}
