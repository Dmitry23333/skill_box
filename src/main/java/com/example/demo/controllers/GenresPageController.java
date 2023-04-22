package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/genres")
public class GenresPageController {
    public GenresPageController() {
    }

    @GetMapping("")
    public String genresPage(Model model) {
        // model.addAttribute("bookData", bookService.getBooksData());
        return "genres/index";
    }

    @GetMapping("/slug")
    public String slugPage(Model model) {
        // model.addAttribute("bookData", bookService.getBooksData());
        return "genres/slug";
    }
}
