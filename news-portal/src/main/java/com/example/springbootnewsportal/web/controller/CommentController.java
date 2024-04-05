package com.example.springbootnewsportal.web.controller;

import com.example.springbootnewsportal.aop.Accessible;
import com.example.springbootnewsportal.entity.Comment;
import com.example.springbootnewsportal.mapper.CommentMapper;
import com.example.springbootnewsportal.service.CommentService;
import com.example.springbootnewsportal.web.model.request.comment.UpsertCommentRequest;
import com.example.springbootnewsportal.web.model.response.comment.CommentListResponse;
import com.example.springbootnewsportal.web.model.response.comment.CommentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    private final CommentMapper commentMapper;

    /*
    @GetMapping
    public ResponseEntity<CommentListResponse> findAll() {
        return ResponseEntity.ok(
                commentMapper.commentToCommentResponseList(
                        commentService.findAll()
                )
        );
    }
    */

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                commentMapper.commentToResponse(
                        commentService.findById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody @Valid UpsertCommentRequest request) {
        Comment newComment = commentService.save(commentMapper.RequestToComment(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentMapper.commentToResponse(newComment));
    }

    @PutMapping("/{id}/{authorId}")
    @Accessible
    public ResponseEntity<CommentResponse> update(@PathVariable("id") Long commentId, @PathVariable("authorId") Long authorId, @RequestBody @Valid UpsertCommentRequest request) {
        Comment updatedComment = commentService.update(commentMapper.RequestToComment(commentId, request));

        return ResponseEntity.ok(commentMapper.commentToResponse(updatedComment));
    }

    @DeleteMapping("/{id}/{authorId}")
    @Accessible
    public ResponseEntity<Void> delete(@PathVariable Long id, @PathVariable("authorId") Long authorId) {
        commentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
