package com.example.spring_jwt_auth_example.mapper;

import com.example.spring_jwt_auth_example.entity.Comment;
import com.example.spring_jwt_auth_example.service.PostService;
import com.example.spring_jwt_auth_example.service.UserService;
import com.example.spring_jwt_auth_example.web.model.request.comment.UpsertCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommentMapperDelegate implements CommentMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Override
    public Comment RequestToComment(UpsertCommentRequest request) {
        Comment comment = new Comment();
        comment.setPost(postService.findById(request.getPostId()));
        comment.setComment(request.getComment());
        return comment;
    }
    @Override
    public Comment RequestToComment(Long commentId, UpsertCommentRequest request) {
        Comment comment = RequestToComment(request);
        comment.setId(commentId);
        return comment;
    }
}
