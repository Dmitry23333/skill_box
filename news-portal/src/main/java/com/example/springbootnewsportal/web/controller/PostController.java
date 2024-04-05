package com.example.springbootnewsportal.web.controller;

import com.example.springbootnewsportal.aop.Accessible;
import com.example.springbootnewsportal.entity.Post;
import com.example.springbootnewsportal.mapper.PostMapper;
import com.example.springbootnewsportal.service.PostService;
import com.example.springbootnewsportal.web.model.request.PostFilterRequest;
import com.example.springbootnewsportal.web.model.request.post.UpsertPostRequest;
import com.example.springbootnewsportal.web.model.response.post.PostListResponse;
import com.example.springbootnewsportal.web.model.response.post.PostResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final PostMapper postMapper;

    @GetMapping()
    public ResponseEntity<PostListResponse> findAll(@Valid PostFilterRequest filter) {
        return ResponseEntity.ok(
                postMapper.postListToPostListResponse(postService.filterBy(filter))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                postMapper.postToResponse(
                        postService.findById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody @Valid UpsertPostRequest request) {
        Post newPost = postService.save(postMapper.requestToPost(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postMapper.postToResponse(newPost));
    }

    @PutMapping("/{id}/{authorId}")
    @Accessible
    public ResponseEntity<PostResponse> update(@PathVariable("id") Long postId, @PathVariable("authorId") Long authorId, @RequestBody @Valid UpsertPostRequest request) {
        Post updatedPost = postService.update(postMapper.requestToPost(postId, request));

        return ResponseEntity.ok(postMapper.postToResponse(updatedPost));
    }

    @DeleteMapping("/{id}/{authorId}")
    @Accessible
    public ResponseEntity<Void> delete(@PathVariable Long id, @PathVariable Long authorId) {
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
