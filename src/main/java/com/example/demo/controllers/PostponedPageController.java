package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostponedPageController {

    public PostponedPageController() {
    }

    @GetMapping("/postponed")
    public String posponedPage() {
        return "/postponed";
    }
}
