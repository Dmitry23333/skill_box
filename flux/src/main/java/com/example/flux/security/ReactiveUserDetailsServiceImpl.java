package com.example.flux.security;


import com.example.flux.entity.User;
import com.example.flux.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {
    private final UserService userService;
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        Mono<User> data = userService.findByUsername(username);
        return data.cast(UserDetails.class);
    }
}



