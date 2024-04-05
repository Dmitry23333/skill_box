package com.example.springbootnewsportal.service;

import com.example.springbootnewsportal.entity.Post;
import com.example.springbootnewsportal.web.model.request.PostFilterRequest;

import java.util.List;

public interface PostService {

    List<Post> filterBy(PostFilterRequest filter);

    List<Post> findAll();

    Post findById(Long id);

    Post save(Post post);

    Post update(Post post);

    void deleteById(Long id);

    boolean check(Long id);
}
