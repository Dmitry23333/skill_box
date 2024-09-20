package com.example.spring_jwt_auth_example.service;


import com.example.spring_jwt_auth_example.aop.Accessible;
import com.example.spring_jwt_auth_example.aop.Belonging;
import jakarta.servlet.http.HttpServletRequest;

public interface AccessCheckerService {
    boolean check(HttpServletRequest request, Accessible accessible);
    boolean isOwner(HttpServletRequest request, Belonging belonging);
}
