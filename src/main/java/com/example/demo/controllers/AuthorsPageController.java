package com.example.demo.controllers;


import com.example.demo.data.BooksPageDto;
import com.example.demo.services.AuthorService;
import com.example.demo.services.BookService;
import com.example.demo.struct.author.AuthorEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Api(description = "authors data")
public class AuthorsPageController {

    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AuthorsPageController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @ModelAttribute("authorMap")
    public Map<String, List<AuthorEntity>> authorMap() {
        return authorService.getAuthorsMap();
    }

    @GetMapping("/authors")
    public String authorsPage() {
        return "/authors/index";
    }


    @ApiOperation("method to get map of authors")
    @GetMapping("/api/authors")
    @ResponseBody
    public Map<String, List<AuthorEntity>> authors() {
        return authorService.getAuthorsMap();
    }


    @GetMapping(value = {"/authors/{id}"})
    public String getAuthorById(@PathVariable(value = "id", required = false) Integer id,
                                Model model) {
        model.addAttribute("id", id);
        model.addAttribute("author", authorService.getAuthorById(id));
        model.addAttribute("books", authorService.getAuthorById(id).getBookEntityList());
        return "/authors/slug";
    }


    @GetMapping(value = {"/books/author/{id}"})
    public String getBooksByGenreId(@PathVariable(value = "id", required = false) Integer id,
                                    Model model) {
        model.addAttribute("id", id);
        model.addAttribute("author", authorService.getAuthorById(id));
        model.addAttribute("booksByAuthor", bookService.getPageOfBooksByAuthor(id, 0, 5).getContent());
        return "/books/author";
    }

    @GetMapping("/books/author/page/{id}")
    @ResponseBody
    public BooksPageDto getNextPageOfBooksByGenreId(@RequestParam("offset") Integer offset,
                                                    @RequestParam("limit") Integer limit,
                                                    @PathVariable Integer id) {
        return new BooksPageDto(bookService.getPageOfBooksByAuthor(id, offset, limit).getContent());
    }
}
