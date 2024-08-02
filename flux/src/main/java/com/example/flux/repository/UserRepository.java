package com.example.flux.repository;

import com.example.flux.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Flux<User> findUserById(String id);
    Mono<User> getUserById(String id);
}
