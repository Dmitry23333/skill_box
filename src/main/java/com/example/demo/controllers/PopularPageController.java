package com.example.demo.controllers;

import com.example.demo.services.BooksRatingAndPopularityService;
import com.example.demo.struct.book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class PopularPageController {
    private final BooksRatingAndPopularityService booksRatingAndPopularityService;

    @Autowired
    public PopularPageController(BooksRatingAndPopularityService booksRatingAndPopularityService) {
        this.booksRatingAndPopularityService = booksRatingAndPopularityService;
    }


    @GetMapping("/books/popular")
    public String popularPage() {
        return "books/popular";
    }

    @ModelAttribute("popular")
    public List<BookEntity> popularBooks() {
        return booksRatingAndPopularityService.getPageOfPopularBooks(0, 5).getContent();
    }

    @Scheduled(fixedRate = 86400000)
    public void updatePopularity() {
        booksRatingAndPopularityService.updatePopularity();
    }
}
