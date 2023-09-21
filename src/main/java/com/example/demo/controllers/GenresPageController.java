package com.example.demo.controllers;

import com.example.demo.data.BooksPageDto;
import com.example.demo.services.BookService;
import com.example.demo.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GenresPageController {

    private final GenreService genreService;
    private final BookService bookService;

    @Autowired
    public GenresPageController(GenreService genreService, BookService bookService) {
        this.genreService = genreService;
        this.bookService = bookService;
    }

    @GetMapping("/genres")
    public String genresPage(Model model) {
        model.addAttribute("genres", genreService.getGenres());
        return "genres/index";
    }

    @GetMapping(value = {"/genres/{id}"})
    public String getBooksByGenreId(@PathVariable(value = "id", required = false) Integer id,
                                    Model model) {
        model.addAttribute("id", id);
        model.addAttribute("genre", genreService.getGenreById(id));
        model.addAttribute("genres", genreService.getGenres());
        model.addAttribute("booksByGenre", bookService.getPageOfBooksByGenreId(id, 0, 5).getContent());
        return "/genres/slug";
    }

    @GetMapping("/genres/page/{id}")
    @ResponseBody
    public BooksPageDto getNextPageOfBooksByGenreId(@RequestParam("offset") Integer offset,
                                                    @RequestParam("limit") Integer limit,
                                                    @PathVariable Integer id) {
        return new BooksPageDto(bookService.getPageOfBooksByGenreId(id, offset, limit).getContent());
    }
}
