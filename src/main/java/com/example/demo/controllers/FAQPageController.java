package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FAQPageController {
    public FAQPageController() {
    }

    @GetMapping("/faq")
    public String faqPage() {
        return "/faq";
    }
}
