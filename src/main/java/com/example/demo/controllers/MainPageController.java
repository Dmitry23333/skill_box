package com.example.demo.controllers;


import com.example.demo.data.BookRepository;
import com.example.demo.data.BookService;
import com.example.demo.data.BooksPageDto;
import com.example.demo.data.SearchWordDto;
import com.example.demo.struct.book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {


    private final BookService bookService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("booksList")
    public List<BookEntity> recommendedBooks(){
        return bookService.getPageOfRecommendedBooks(0,6).getContent();
    }

    @ModelAttribute("recentBooks")
    public List <BookEntity> recentBooks() {
        return bookService.getPageOfRecentBooks(0,6).getContent();
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksPageDto getBooksPage(@RequestParam("offset") Integer offset,
                                     @RequestParam("limit") Integer limit){
        return new BooksPageDto(bookService.getPageOfRecommendedBooks(offset,limit).getContent());
    }

  //  @GetMapping("/books/recent")
  //  @ResponseBody
  //  public BooksPageDto getBooksRecentPage(@RequestParam("offset") Integer offset,
  //                                   @RequestParam("limit") Integer limit){
  //      return new BooksPageDto(bookService.getPageOfRecentBooks(offset,limit).getContent());
  //  }


}
