package com.example.rest.demo.service;

import com.example.rest.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User save (User user);

    User update(User user);

    void deleteById(Long id);
}
