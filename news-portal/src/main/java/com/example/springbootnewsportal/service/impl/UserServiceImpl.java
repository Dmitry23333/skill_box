package com.example.springbootnewsportal.service.impl;

import com.example.springbootnewsportal.entity.User;
import com.example.springbootnewsportal.repository.UserRepository;
import com.example.springbootnewsportal.service.UserService;
import com.example.springbootnewsportal.utils.BeanUtils;
import com.example.springbootnewsportal.web.PaginationRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll(PaginationRequest request) {
        return userRepository.findAll(request.pageRequest()).getContent();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Entity by ID {0} not found", id)));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User existedUser = findById(user.getId());
        BeanUtils.copyNonNullProperties(user, existedUser);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
