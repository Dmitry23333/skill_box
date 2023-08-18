package com.example.demo.services;

import com.example.demo.repositories.BookRepository;
import com.example.demo.struct.book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksRatingAndPopularityService {
    private final BookRepository bookRepository;

    @Autowired
    public BooksRatingAndPopularityService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> updatePopularity() {
        List<BookEntity> bookEntities = bookRepository.findAll();
        for (BookEntity bookEntity : bookEntities) {
            bookEntity.setPopularity();
        }
        return bookRepository.saveAll(bookEntities);
    }


    public Page<BookEntity> getPageOfPopularBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getPopularBooks(nextPage);
    }
}
