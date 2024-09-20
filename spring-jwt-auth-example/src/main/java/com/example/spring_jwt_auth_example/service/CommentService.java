package com.example.spring_jwt_auth_example.service;

import com.example.spring_jwt_auth_example.entity.Comment;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    Comment findById(Long id);
    Comment save(HttpServletRequest request, Comment comment);
    Comment update(HttpServletRequest request, Comment comment);
    void deleteById(Long id);
    boolean check(Long id);
}
