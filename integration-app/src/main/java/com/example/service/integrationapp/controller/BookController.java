package com.example.service.integrationapp.controller;

import com.example.service.integrationapp.clients.OpenFeignClient;

import com.example.service.integrationapp.model.request.UpsertBookRequest;
import com.example.service.integrationapp.model.response.BookListResponse;
import com.example.service.integrationapp.model.response.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/client/book")
public class BookController {
    private final OpenFeignClient client;

    @GetMapping
    public ResponseEntity<BookListResponse> findAll() {
        return ResponseEntity.ok(client.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(client.findById(id));
    }

    @GetMapping("/byCategory")
    public ResponseEntity<BookListResponse> findByCategory(@RequestParam String categoryName) {
        return ResponseEntity.ok(client.findByCategory(categoryName));
    }

    @GetMapping("/byAuthorAndTitle")
    public ResponseEntity<BookResponse> findByAuthorAndTitle(String author, @RequestParam String title) {
        return ResponseEntity.ok(client.findByAuthorAndTitle(author, title));
    }

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody UpsertBookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(client.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable("id") UUID bookId, @RequestBody UpsertBookRequest request) {
        return ResponseEntity.ok(client.update(bookId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return client.delete(id);
    }

}
