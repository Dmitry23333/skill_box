package com.example.springbootnewsportal.service;

import com.example.springbootnewsportal.aop.Accessible;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

public abstract class AbstractAccessCheckerService implements AccessCheckerService {
    private static final String ID = "authorId";

    @Override
    @SuppressWarnings("unchecked")
    public boolean check(HttpServletRequest request, Accessible accessible) {
        var pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        return check(Long.valueOf(pathVariables.get(ID)));
    }

    protected abstract boolean check(Long authorId);
}
