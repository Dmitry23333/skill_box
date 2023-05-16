package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchPageController {
    public SearchPageController() {
    }

    @GetMapping("/search")
    public String searchPage() {
        return "/search/index";
    }
}
