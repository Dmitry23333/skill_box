package com.example.demo.services;

import com.example.demo.repositories.GenreRepository;
import com.example.demo.struct.genre.GenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreEntity> getGenres() {
        return genreRepository.findAll();
    }

    public GenreEntity getGenreById(Integer id) {
        return genreRepository.getGenreEntitiesById(id);
    }
}

