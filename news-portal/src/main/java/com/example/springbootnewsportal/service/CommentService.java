package com.example.springbootnewsportal.service;

import com.example.springbootnewsportal.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    Comment findById(Long id);

    Comment save(Comment comment);

    Comment update(Comment comment);

    void deleteById(Long id);

    boolean check(Long id);
}
