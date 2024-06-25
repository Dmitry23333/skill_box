package com.example.service.integration.service;

import com.example.service.integration.entity.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {

    List<Book> findAll();

    Book save(Book book);

    Book update(Book book);

    void deleteById(UUID id);

    Book findById(UUID id);

    Book findBookByAuthorAndTitle(String author, String title);

    List<Book> findBooksByCategory(String categoryName);
}
