package com.example.spring_jwt_auth_example.service.impl;


import com.example.spring_jwt_auth_example.entity.User;
import com.example.spring_jwt_auth_example.exception.AlreadyExistsException;
import com.example.spring_jwt_auth_example.repository.UserRepository;
import com.example.spring_jwt_auth_example.secutiry.SecurityService;
import com.example.spring_jwt_auth_example.service.UserService;
import com.example.spring_jwt_auth_example.utils.BeanUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SecurityService securityService;

    @Override
    public List<User> findAll(/*PaginationRequest request*/) {
        return userRepository.findAll(/*request.pageRequest()).getContent()*/);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Entity by ID {0} not found", id)));
    }
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Entity by name {0} not found", username)));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User existedUser = findById(user.getId());
        BeanUtils.copyNonNullProperties(user, existedUser);
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new AlreadyExistsException("Username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException("Email already exists");
        }
        securityService.update(user);
        return user;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public User getUser(HttpServletRequest request) {
        var authentication = (Authentication) request.getUserPrincipal();
        var userDetails = (UserDetails) authentication.getPrincipal();
        return findByUsername(userDetails.getUsername());
    }

}
