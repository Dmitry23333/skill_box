package com.example.springbootnewsportal.service.impl;

import com.example.springbootnewsportal.entity.Post;
import com.example.springbootnewsportal.entity.User;
import com.example.springbootnewsportal.repository.PostRepository;
import com.example.springbootnewsportal.repository.PostSpecification;
import com.example.springbootnewsportal.service.PostService;
import com.example.springbootnewsportal.service.UserService;
import com.example.springbootnewsportal.utils.BeanUtils;
import com.example.springbootnewsportal.web.model.request.PostFilterRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    public Post save(Post post) {
        User user = userService.findById(post.getAuthor().getId());
        post.setAuthor(user);
        return postRepository.save(post);
    }

    @Override
    public Post update(Post post) {
        User user = userService.findById(post.getAuthor().getId());
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
