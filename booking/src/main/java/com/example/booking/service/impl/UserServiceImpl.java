package com.example.booking.service.impl;

import com.example.booking.entity.Room;
import com.example.booking.entity.User;
import com.example.booking.exception.AlreadyExistsException;
import com.example.booking.exception.EntityNotFoundException;
import com.example.booking.repository.UserRepository;
import com.example.booking.service.UserService;
import com.example.booking.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Entity by ID {0} not found", id)));
    }

    @Override
    public User save(User user) {
        if (!userRepository.existsUserByName(user.getName()) && !userRepository.existsUserByEmail(user.getEmail())) {
            return userRepository.save(user);
        } else {
            throw new AlreadyExistsException(
                    MessageFormat.format("Username {0} or email {1} exist", user.getName(), user.getEmail()));
        }
    }

    @Override
    public User update(User user) {
        User existedUser = findById(user.getId());
        BeanUtils.copyNonNullProperties(user, existedUser);
        return userRepository.save(user);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Entity by name {0} not found", name)));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsUserByName(String name) {
        return userRepository.existsUserByName(name);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }
}
