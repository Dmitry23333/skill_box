package com.example.spring_jwt_auth_example.service.impl;


import com.example.spring_jwt_auth_example.entity.Post;
import com.example.spring_jwt_auth_example.entity.User;
import com.example.spring_jwt_auth_example.repository.PostRepository;
import com.example.spring_jwt_auth_example.repository.PostSpecification;
import com.example.spring_jwt_auth_example.service.PostService;
import com.example.spring_jwt_auth_example.service.UserService;
import com.example.spring_jwt_auth_example.utils.BeanUtils;
import com.example.spring_jwt_auth_example.web.model.request.PostFilterRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    @Override
    public List<Post> filterBy(PostFilterRequest filter) {
        return postRepository.findAll(PostSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())
        ).getContent();
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Post by {0} not found", id
                )));
    }

    @Override
    public Post save(HttpServletRequest request, Post post) {
        User user = userService.getUser(request);
        post.setAuthor(user);
        return postRepository.save(post);
    }

    @Override
    public Post update(HttpServletRequest request, Post post) {
        var authentication = (Authentication) request.getUserPrincipal();
        var userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.getUser(request);
        Post existedPost = findById(post.getId());
        BeanUtils.copyNonNullProperties(post, existedPost);
        existedPost.setAuthor(user);
        return postRepository.save(existedPost);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
    @Override
    public boolean check(Long id) {
        return postRepository.existsPostByAuthor_Id(id);
    }
}
