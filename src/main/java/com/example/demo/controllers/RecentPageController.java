package com.example.demo.controllers;

import com.example.demo.data.BookService;
import com.example.demo.data.BooksPageDto;
import com.example.demo.data.DateDto;
import com.example.demo.struct.book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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


    @ModelAttribute("getBetweenPubDate")
    public List<BookEntity> getBetweenPubDate() {
        return new ArrayList<>();
    }

    @GetMapping("/books/recent/startPage")
    @ResponseBody
    public BooksPageDto getBooksRecentPage(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit){
        return new BooksPageDto(bookService.getPageOfRecentBooks(offset,limit).getContent());
    }


    @GetMapping(value = {"/books/recent/", "/books/recent/{from}:{to}"})
    public String getBetweenPubDateResults(@PathVariable(value = "from", required = false) String from,
                                    @PathVariable(value = "to", required = false) String to,
                                    Model model) {
        model.addAttribute("from",  from);
        model.addAttribute("to", to);
        model.addAttribute("getBetweenPubDate", bookService.getPageOfSearchDateBetweenResultBooks(from,
                to, 0, 6).getContent());
        return "/books/recent";
    }


    @GetMapping("/books/recent/page/{from}:{to}")
    @ResponseBody
    public BooksPageDto getNextPage(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit,
                                           @PathVariable(value = "from", required = false) String from,
                                           @PathVariable(value = "to", required = false) String to) {
        return new BooksPageDto(bookService.getPageOfSearchDateBetweenResultBooks(from, to, offset, limit).getContent());
    }
}
