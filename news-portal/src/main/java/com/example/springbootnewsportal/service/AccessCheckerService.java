package com.example.springbootnewsportal.service;

import com.example.springbootnewsportal.aop.Accessible;
import jakarta.servlet.http.HttpServletRequest;

public interface AccessCheckerService {
    boolean check(HttpServletRequest request, Accessible accessible);
}
