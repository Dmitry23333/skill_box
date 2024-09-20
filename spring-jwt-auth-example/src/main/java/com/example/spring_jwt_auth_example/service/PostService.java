package com.example.spring_jwt_auth_example.service;



import com.example.spring_jwt_auth_example.entity.Post;
import com.example.spring_jwt_auth_example.web.model.request.PostFilterRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface PostService {
    List<Post> filterBy(PostFilterRequest filter);
    List<Post> findAll();
    Post findById(Long id);
    Post save(HttpServletRequest request,Post post);
    Post update(HttpServletRequest request,Post post);
    void deleteById(Long id);
    boolean check(Long id);
}
