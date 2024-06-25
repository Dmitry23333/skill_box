package com.example.service.integration.service.impl;

import com.example.service.integration.entity.Book;
import com.example.service.integration.repository.BookRepository;
import com.example.service.integration.service.BookService;
import com.example.service.integration.utils.BeanUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

import static com.example.service.integration.configuration.properties.AppCacheProperties.CacheNames.*;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheManager = "redisCacheManager")
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;


    @Override
    @Cacheable(cacheNames = BOOKS)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }


    @Override
    @Cacheable(cacheNames = BOOK_BY_ID, key = "#id")
    public Book findById(UUID id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Entity by ID {0} not found", id)));
    }


    @Override
    @Cacheable(cacheNames = BOOK_BY_AUTHOR_AND_TITLE, key = "#author + #title")
    public Book findBookByAuthorAndTitle(String author, String title) {
        return bookRepository.findFirstByAuthorAndTitle(author, title);
    }

    @Override
    @Cacheable(cacheNames = BOOKS_BY_CATEGORY, key = "#categoryName")
    public List<Book> findBooksByCategory(String categoryName) {
        return bookRepository.findAllByCategory_Name(categoryName);
    }


    @Override
    @Caching(evict = {
            @CacheEvict(value = BOOK_BY_ID, key = "#book.id", beforeInvocation = true),
            @CacheEvict(value = BOOKS_BY_CATEGORY, key = "#book.category.name", beforeInvocation = true),
            @CacheEvict(value = BOOK_BY_AUTHOR_AND_TITLE, key = "#book.author + #book.title", beforeInvocation = true),
            @CacheEvict(value = BOOKS, allEntries = true)
    })
    public Book update(Book book) {
        Book existedBook = findById(book.getId());
        BeanUtils.copyNonNullProperties(book, existedBook);
        return bookRepository.save(book);
    }


    @Override
    @Caching(evict =
            {
                    @CacheEvict(value = BOOK_BY_ID, key = "#id", beforeInvocation = true),
                    @CacheEvict(value = BOOKS, allEntries = true)
            })
    public void deleteById(UUID id) {
        bookRepository.deleteById(id);
    }


    @Override
    @Caching(evict = {
            @CacheEvict(value = BOOKS_BY_CATEGORY, key = "#book.category.name", beforeInvocation = true),
            @CacheEvict(cacheNames = BOOKS, allEntries = true)
    })
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
