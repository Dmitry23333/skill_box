package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocumentsPageController {
    public DocumentsPageController() {
    }

    @GetMapping("/documents")
    public String documentsPage() {
        return "/documents/index";
    }
}
