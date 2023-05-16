package com.example.demo.controllers;

import com.example.demo.data.Book;
import com.example.demo.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class RecentPageController {
    private final BookService bookService;

    @Autowired
    public RecentPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("booksList")
    public List<Book> recentBooks(){
        return bookService.getBooksData();
    }
    @GetMapping("/books/recent")
    public String recentPage() {
        return "/books/recent";
    }
}
