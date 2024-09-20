package com.example.spring_jwt_auth_example.mapper;


import com.example.spring_jwt_auth_example.entity.Post;
import com.example.spring_jwt_auth_example.service.CategoryService;
import com.example.spring_jwt_auth_example.service.CommentService;
import com.example.spring_jwt_auth_example.web.model.request.post.UpsertPostRequest;
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
