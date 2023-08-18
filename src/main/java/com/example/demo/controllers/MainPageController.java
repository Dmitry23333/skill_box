package com.example.demo.controllers;


import com.example.demo.data.*;
import com.example.demo.services.BookService;
import com.example.demo.services.BooksRatingAndPopularityService;
import com.example.demo.services.TagService;
import com.example.demo.struct.book.BookEntity;
import com.example.demo.struct.tags.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainPageController {


    private final BookService bookService;
    private final TagService tagService;
    private final BooksRatingAndPopularityService booksRatingAndPopularityService;

    @Autowired
    public MainPageController(BookService bookService, TagService tagService, BooksRatingAndPopularityService booksRatingAndPopularityService) {
        this.bookService = bookService;
        this.tagService = tagService;
        this.booksRatingAndPopularityService = booksRatingAndPopularityService;
    }

    @ModelAttribute("booksList")
    public List<BookEntity> recommendedBooks() {
        return bookService.getPageOfRecommendedBooks(0, 6).getContent();
    }


    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksPageDto getBooksPage(@RequestParam("offset") Integer offset,
                                     @RequestParam("limit") Integer limit) {
        return new BooksPageDto(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }


    @ModelAttribute("booksByDates")
    public List<BookEntity> getRecentBooks() {
        return bookService.getPageOfRecentBooks(0, 20).getContent();
    }

    @GetMapping("/main/recent")
    @ResponseBody
    public BooksPageDto getRecentBooksPage(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit) {
        return new BooksPageDto(bookService.getPageOfRecentBooks(offset, limit).getContent());
    }

    @ModelAttribute("popular")
    public List<BookEntity> popularBooks() {

        return booksRatingAndPopularityService.getPageOfPopularBooks(0, 5).getContent();
    }

    @ModelAttribute("tags")
    public List<TagEntity> tags() {
        return tagService.getTags();
    }

}
