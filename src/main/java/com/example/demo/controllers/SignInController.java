package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

    public SignInController() {
    }

    @GetMapping("/signin")
    public String signInPage() {
        return "/signin";
    }
}
