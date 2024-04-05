package com.example.springbootnewsportal.service.impl;

import com.example.springbootnewsportal.service.AbstractAccessCheckerService;
import com.example.springbootnewsportal.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Primary
public class PostAccessCheckerImpl extends AbstractAccessCheckerService {

    private final PostService postService;

    @Override
    protected boolean check(Long authorId) {
        return postService.check(authorId);
    }
}
