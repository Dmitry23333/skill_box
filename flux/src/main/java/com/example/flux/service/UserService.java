package com.example.flux.service;

import com.example.flux.entity.RoleType;
import com.example.flux.entity.User;
import com.example.flux.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    public Flux<User> findAll() {
        return repository.findAll();
    }
    public Mono<User> findById(String id) {
        return repository.findById(id);
    }
    public Mono<User> update(String id, User user) {
        return findById(id).flatMap(userForUpdate -> {
            if (StringUtils.hasText(user.getUsername())) {
                userForUpdate.setUsername(user.getUsername());
            }
            if (StringUtils.hasText(user.getEmail())) {
                userForUpdate.setEmail(user.getEmail());
            }
            return repository.save(userForUpdate);
        });
    }
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
    public Mono<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }
    public Mono<User> createNewAccount(User user, RoleType roleType) {
        user.setRoles(Collections.singleton(roleType));
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
}
