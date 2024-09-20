package com.example.spring_jwt_auth_example.service;


import com.example.spring_jwt_auth_example.entity.RoleType;
import com.example.spring_jwt_auth_example.entity.User;
import com.example.spring_jwt_auth_example.web.PaginationRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserService {
    List<User> findAll(/*PaginationRequest request*/);
    User findById(Long id);
    User findByUsername(String username);
    User save(User user);
    User update(User user);
    void deleteById(Long id);
    User getUser(HttpServletRequest request);
}
