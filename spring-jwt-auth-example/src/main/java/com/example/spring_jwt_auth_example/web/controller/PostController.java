package com.example.spring_jwt_auth_example.web.controller;


import com.example.spring_jwt_auth_example.aop.Accessible;
import com.example.spring_jwt_auth_example.aop.Belonging;
import com.example.spring_jwt_auth_example.entity.Post;
import com.example.spring_jwt_auth_example.mapper.PostMapper;
import com.example.spring_jwt_auth_example.service.PostService;
import com.example.spring_jwt_auth_example.web.model.request.PostFilterRequest;
import com.example.spring_jwt_auth_example.web.model.request.post.UpsertPostRequest;
import com.example.spring_jwt_auth_example.web.model.response.post.PostListResponse;
import com.example.spring_jwt_auth_example.web.model.response.post.PostResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;
    @GetMapping()
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<PostListResponse> findAll(@Valid PostFilterRequest filter) {
        return ResponseEntity.ok(
                postMapper.postListToPostListResponse(postService.filterBy(filter))
        );
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<PostResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                postMapper.postToResponse(
                        postService.findById(id)
                )
        );
    }
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<PostResponse> create(HttpServletRequest request, @RequestBody @Valid UpsertPostRequest upsertPostRequest) {
        Post newPost = postService.save(request, postMapper.requestToPost(upsertPostRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postMapper.postToResponse(newPost));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @Belonging
    public ResponseEntity<PostResponse> update(HttpServletRequest request, @PathVariable("id") Long postId, @RequestBody @Valid UpsertPostRequest upsertPostRequest) {
        Post updatedPost = postService.update(request, postMapper.requestToPost(postId, upsertPostRequest));
        return ResponseEntity.ok(postMapper.postToResponse(updatedPost));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @Accessible
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
