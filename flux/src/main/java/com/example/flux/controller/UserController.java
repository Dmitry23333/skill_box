package com.example.flux.controller;

import com.example.flux.entity.RoleType;
import com.example.flux.exception.UserAlreadyExistException;
import com.example.flux.mapper.UserMapper;
import com.example.flux.service.UserService;
import com.example.flux.web.requset.UpsertUserRequest;
import com.example.flux.web.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','MANAGER')")
    public Flux<UserResponse> getAllUsers() {
        return userService.findAll().map(userMapper::userToResponse);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER')")
    public Mono<ResponseEntity<UserResponse>> getById(@PathVariable String id) {
        return userService.findById(id)
                .map(userMapper::userToResponse)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER')")
    public Mono<ResponseEntity<UserResponse>> updateUser(@PathVariable String id, @RequestBody UpsertUserRequest userRequest) {
        return userService.update(id, userMapper.requestToUser(userRequest))
                .map(userMapper::userToResponse)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER')")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable String id) {
        return userService.deleteById(id).then(Mono.just(ResponseEntity.noContent().build()));
    }
    @PostMapping("/account")
    public Mono<ResponseEntity<UserResponse>> createUser(@RequestBody UpsertUserRequest userRequest, @RequestParam RoleType roleType) throws UserAlreadyExistException {
        return userService.createNewAccount(userMapper.requestToUser(userRequest), roleType)
                .map(userMapper::userToResponse)
                .map(ResponseEntity::ok);
    }
}
