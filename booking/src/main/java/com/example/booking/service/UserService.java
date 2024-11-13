package com.example.booking.service;

import com.example.booking.entity.Hotel;
import com.example.booking.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    User save(User user);

    User update(User user);
    User findByName(String name);

    void deleteById(Long id);

    boolean existsUserByName(String name);

    boolean existsUserByEmail(String email);
}
