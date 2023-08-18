package com.example.demo.controllers;

import com.example.demo.services.BookService;
import com.example.demo.struct.book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RecentPageController {
    private final BookService bookService;

    @Autowired
    public RecentPageController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/books/recent")
    public String mainPage() {
        return "books/recent";
    }


    @ModelAttribute("booksByDates")
    public List<BookEntity> getRecentBooks() {
        return bookService.getPageOfRecentBooks(0, 20).getContent();
    }

}
