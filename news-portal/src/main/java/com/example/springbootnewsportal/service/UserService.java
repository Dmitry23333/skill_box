package com.example.springbootnewsportal.service;

import com.example.springbootnewsportal.entity.User;
import com.example.springbootnewsportal.web.PaginationRequest;

import java.util.List;

public interface UserService {

    List<User> findAll(PaginationRequest request);

    User findById(Long id);

    User save(User user);

    User update(User user);

    void deleteById(Long id);
}
