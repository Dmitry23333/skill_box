package com.example.demo.services;


import com.example.demo.repositories.AuthorRepository;
import com.example.demo.struct.author.AuthorEntity;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Map<String, List<AuthorEntity>> getAuthorsMap() {
        return authorRepository.findAll().stream().collect(Collectors.groupingBy((AuthorEntity a) -> a.getName().substring(0, 1)));
    }

    public AuthorEntity getAuthorById(Integer id) {
        return authorRepository.getById(id);
    }
}
