package com.example.service.integrationapp.clients;

import com.example.service.integrationapp.model.request.UpsertBookRequest;
import com.example.service.integrationapp.model.response.BookListResponse;
import com.example.service.integrationapp.model.response.BookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@FeignClient(name = "client1", url = "${app.integration.base-url}")
public interface OpenFeignClient {

    @GetMapping(value = "api/book")
    BookListResponse findAll();

    @GetMapping(value = "api/book/{id}")
    BookResponse findById(@PathVariable UUID id);

    @GetMapping("api/book/byCategory")
    BookListResponse findByCategory(@RequestParam String categoryName);

    @GetMapping("api/book/byAuthorAndTitle")
    BookResponse findByAuthorAndTitle(@RequestParam String author, @RequestParam String title);

    @PostMapping("api/book")
    BookResponse create(@RequestBody UpsertBookRequest request);

    @PutMapping("api/book/{id}")
    BookResponse update(@PathVariable("id") UUID bookId, @RequestBody UpsertBookRequest request);

    @DeleteMapping("api/book/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id);


}



