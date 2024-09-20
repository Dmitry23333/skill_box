package com.example.spring_jwt_auth_example.service.impl;

import com.example.spring_jwt_auth_example.aop.Accessible;
import com.example.spring_jwt_auth_example.aop.Belonging;
import com.example.spring_jwt_auth_example.entity.RoleType;
import com.example.spring_jwt_auth_example.entity.User;
import com.example.spring_jwt_auth_example.service.AccessCheckerService;
import com.example.spring_jwt_auth_example.service.PostService;
import com.example.spring_jwt_auth_example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
@Primary
public class PostAccessCheckerImpl implements AccessCheckerService {
    private final PostService postService;
    private final UserService userService;
    private static final String ID = "id";
    @Override
    public boolean check(HttpServletRequest request, Accessible accessible) {
        var pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        User user = userService.getUser(request);
        boolean result;
        result = user.getRoles().contains(RoleType.ROLE_ADMIN) || user.getRoles().contains(RoleType.ROLE_MODERATOR);
        if (user.getRoles().contains(RoleType.ROLE_USER) &&
                Objects.equals(postService.findById(Long.valueOf(pathVariables.get(ID))).getAuthor().getId(), user.getId())) {
            result = true;
        }
        return result;
    }
    @Override
    public boolean isOwner(HttpServletRequest request, Belonging belonging) {
        User user = userService.getUser(request);
        var pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        return Objects.equals(postService.findById(Long.valueOf(pathVariables.get(ID))).getAuthor().getId(), user.getId());
    }
}
