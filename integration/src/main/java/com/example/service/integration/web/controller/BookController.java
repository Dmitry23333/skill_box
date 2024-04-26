package com.example.service.integration.web.controller;

import com.example.service.integration.entity.Book;
import com.example.service.integration.mapper.BookMapper;
import com.example.service.integration.service.BookService;
import com.example.service.integration.web.model.request.UpsertBookRequest;
import com.example.service.integration.web.model.response.BookListResponse;
import com.example.service.integration.web.model.response.BookResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.text.MessageFormat;
import java.util.UUID;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;


    @GetMapping
    public ResponseEntity<BookListResponse> findAll() {
        return ResponseEntity.ok(bookMapper.booksToBooksResponseList(bookService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(bookMapper.bookToResponse(bookService.findById(id)));
        } catch (EntityNotFoundException | MethodArgumentTypeMismatchException exception) {
            throw new EntityNotFoundException(MessageFormat.format("Book with {0} not found", id));
        }

    }

    @GetMapping("/byCategory")
    public ResponseEntity<BookListResponse> findByCategory(@RequestParam String categoryName) {
        return ResponseEntity.ok(bookMapper.booksToBooksResponseList(bookService.findBooksByCategory(categoryName)));
    }

    @GetMapping("/byAuthorAndTitle")
    public ResponseEntity<BookResponse> findByAuthorAndTitle(@RequestParam String author, @RequestParam String title) {
        try {
            return ResponseEntity.ok(bookMapper.bookToResponse(bookService.findBookByAuthorAndTitle(author, title)));
        } catch (NullPointerException ex) {
            throw new EntityNotFoundException(MessageFormat.format("Book by {0} and title {1} not found", author, title));
        }
    }


    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody UpsertBookRequest request) {
        Book newBook = bookService.save(bookMapper.requestToBook(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookMapper.bookToResponse(newBook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable("id") UUID bookId, @RequestBody UpsertBookRequest request) {
        Book updatedBook = bookService.update(bookMapper.requestToBook(bookId, request));
        return ResponseEntity.ok(bookMapper.bookToResponse(updatedBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
