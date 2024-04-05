package com.example.springbootnewsportal.service.impl;

import com.example.springbootnewsportal.entity.Comment;
import com.example.springbootnewsportal.entity.Post;
import com.example.springbootnewsportal.entity.User;
import com.example.springbootnewsportal.repository.CommentRepository;
import com.example.springbootnewsportal.service.CommentService;
import com.example.springbootnewsportal.service.PostService;
import com.example.springbootnewsportal.service.UserService;
import com.example.springbootnewsportal.utils.BeanUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final UserService userService;

    private final PostService postService;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Post by {0} not found", id
                )));
    }

    @Override
    public Comment save(Comment comment) {
        User user = userService.findById(comment.getUser().getId());
        Post post = postService.findById(comment.getPost().getId());
        comment.setUser(user);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        User user = userService.findById(comment.getUser().getId());
        Comment existedComment = findById(comment.getId());
        Post post = postService.findById(comment.getPost().getId());
        BeanUtils.copyNonNullProperties(comment, existedComment);
        existedComment.setUser(user);
        existedComment.setPost(post);
        return commentRepository.save(existedComment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public boolean check(Long authorId) {
        return commentRepository.existsCommentByUser_Id(authorId);
    }
}
