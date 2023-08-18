package com.example.demo.repositories;

import com.example.demo.struct.book.BookEntity;
import com.example.demo.struct.genre.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {

    GenreEntity getGenreEntitiesById(Integer id);
}
