package com.example.spring_jwt_auth_example.web.controller;

import com.example.spring_jwt_auth_example.aop.Accessible;
import com.example.spring_jwt_auth_example.aop.Belonging;
import com.example.spring_jwt_auth_example.entity.Comment;
import com.example.spring_jwt_auth_example.mapper.CommentMapper;
import com.example.spring_jwt_auth_example.service.CommentService;
import com.example.spring_jwt_auth_example.web.model.request.comment.UpsertCommentRequest;
import com.example.spring_jwt_auth_example.web.model.response.comment.CommentListResponse;
import com.example.spring_jwt_auth_example.web.model.response.comment.CommentResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<CommentListResponse> findAll() {
        return ResponseEntity.ok(
                commentMapper.commentToCommentResponseList(
                        commentService.findAll()
                )
        );
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                commentMapper.commentToResponse(
                        commentService.findById(id)
                )
        );
    }
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<CommentResponse> create(HttpServletRequest request, @RequestBody @Valid UpsertCommentRequest upsertCommentRequest) {
        Comment newComment = commentService.save(request,commentMapper.RequestToComment(upsertCommentRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentMapper.commentToResponse(newComment));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @Belonging
    public ResponseEntity<CommentResponse> update(HttpServletRequest request, @PathVariable("id") Long commentId, @RequestBody @Valid UpsertCommentRequest upsertCommentRequest) {
        Comment updatedComment = commentService.update(request,commentMapper.RequestToComment(commentId, upsertCommentRequest));
        System.out.println("ddd");
        return ResponseEntity.ok(commentMapper.commentToResponse(updatedComment));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @Accessible
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
