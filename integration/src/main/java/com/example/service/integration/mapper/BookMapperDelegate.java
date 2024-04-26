package com.example.service.integration.mapper;

import com.example.service.integration.entity.Book;
import com.example.service.integration.service.CategoryService;
import com.example.service.integration.web.model.request.UpsertBookRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


public abstract class BookMapperDelegate implements BookMapper {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Book requestToBook(UpsertBookRequest request) {
        Book book = new Book();
        book.setAuthor(request.getAuthor());
        book.setTitle(request.getTitle());
        if (!categoryService.existsCategoryByName(request.getCategoryName())) {
            categoryService.save(categoryMapper.requestToCategory(request));
        }
        book.setCategory(categoryService.findByName(request.getCategoryName()));
        return book;
    }

    @Override
    public Book requestToBook(UUID bookId, UpsertBookRequest request) {
        Book book = requestToBook(request);
        book.setId(bookId);
        return book;
    }
}
