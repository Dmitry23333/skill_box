package com.example.spring_jwt_auth_example.mapper;

import com.example.spring_jwt_auth_example.entity.Comment;
import com.example.spring_jwt_auth_example.web.model.request.comment.UpsertCommentRequest;
import com.example.spring_jwt_auth_example.web.model.response.comment.CommentListResponse;
import com.example.spring_jwt_auth_example.web.model.response.comment.CommentResponse;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@DecoratedWith(CommentMapperDelegate.class)
public interface CommentMapper {
    Comment RequestToComment(UpsertCommentRequest request);
    @Mapping(source = "commentId", target = "id")
    Comment RequestToComment(Long commentId, UpsertCommentRequest request);
    CommentResponse commentToResponse(Comment comment);
    default CommentListResponse commentToCommentResponseList(List<Comment> comments) {
        CommentListResponse response = new CommentListResponse();
        response.setComments(comments.stream()
                .map(this::commentToResponse).collect(Collectors.toList()));
        return response;
    }
}
