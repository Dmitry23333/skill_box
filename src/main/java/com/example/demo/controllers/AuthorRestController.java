package com.example.demo.controllers;

import com.example.demo.repositories.AuthorRepository;
import com.example.demo.struct.author.AuthorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("authors")
public class AuthorRestController {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorRestController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("all")
    public List<AuthorEntity> authorsAll() {
        return authorRepository.findAll();
    }

}
