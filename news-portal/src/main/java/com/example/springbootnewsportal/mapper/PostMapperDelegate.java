package com.example.springbootnewsportal.mapper;

import com.example.springbootnewsportal.entity.Post;
import com.example.springbootnewsportal.service.CategoryService;
import com.example.springbootnewsportal.service.CommentService;
import com.example.springbootnewsportal.service.UserService;
import com.example.springbootnewsportal.web.model.request.post.UpsertPostRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PostMapperDelegate implements PostMapper {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentService commentService;

    @Override
    public Post requestToPost(UpsertPostRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setDescription(request.getDescription());
        post.setBody(request.getBody());
        post.setCategory(categoryService.findById(request.getCategoryId()));
        post.setComments(commentService.findAll());
        return post;
    }

    @Override
    public Post requestToPost(Long postId, UpsertPostRequest request) {
        Post post = requestToPost(request);
        post.setId(postId);
        return post;
    }
}
