package com.example.demo.services;

import com.example.demo.repositories.BookRepository;
import com.example.demo.data.BooksPageDto;
import com.example.demo.struct.book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> getBooksData() {
        return bookRepository.findAll();
    }

    public Page<BookEntity> getPageOfRecommendedBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }


    public Page<BookEntity> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBookByTitleContaining(searchWord, nextPage);
    }


    public List<BookEntity> getBooksByAuthorEntityName(String name) {
        return bookRepository.findBooksByAuthorEntityNameContaining(name);
    }


    public List<BookEntity> getBooksByTitle(String title) {
        return bookRepository.findBooksByTitle(title);
    }


    public List<BookEntity> getBooksWithPriceBetween(Integer min, Integer max) {
        return bookRepository.findBooksByPriceBetween(min, max);
    }

    public List<BookEntity> getBooksWithPrice(Integer price) {
        return bookRepository.findBooksByPriceIs(price);
    }


    public List<BookEntity> getBooksWithMaxDiscount() {
        return bookRepository.getBooksWithMaxDiscount();
    }

    public List<BookEntity> getBestsellers() {
        return bookRepository.getBestsellers();
    }


    public Page<BookEntity> getPageOfRecentBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getRecent(nextPage);
    }


    public BooksPageDto getPageOfBetweenDatesBooks(Integer offset, Integer limit, String from, String to) {
        Pageable nextPage = PageRequest.of(offset, limit);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Page<BookEntity> page;
        if (from != null && to != null) {
            try {
                page = bookRepository.getBooksBetweenDates(nextPage, formatter.parse(from), formatter.parse(to));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            page = bookRepository.getRecent(nextPage);
        }
        BooksPageDto booksPageDto = new BooksPageDto();
        booksPageDto.setCount(page.getTotalPages());
        booksPageDto.setBooks(page.getContent());
        return booksPageDto;
    }

    public Page<BookEntity> getPageOfBooksByTag(Integer id, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getBooksByTagID(nextPage, id);
    }

    public Page<BookEntity> getPageOfBooksByGenreId(Integer id, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getBooksByGenreId(nextPage, id);
    }

    public Page<BookEntity> getPageOfBooksByAuthor(Integer id, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getBooksByAuthorId(nextPage, id);
    }
}
