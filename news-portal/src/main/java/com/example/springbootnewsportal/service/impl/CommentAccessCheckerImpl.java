package com.example.springbootnewsportal.service.impl;

import com.example.springbootnewsportal.service.AbstractAccessCheckerService;
import com.example.springbootnewsportal.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentAccessCheckerImpl extends AbstractAccessCheckerService {

    private final CommentService commentService;

    @Override
    protected boolean check(Long authorId) {
        return commentService.check(authorId);
    }
}
