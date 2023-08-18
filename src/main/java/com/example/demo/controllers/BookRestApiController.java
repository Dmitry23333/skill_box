package com.example.demo.controllers;


import com.example.demo.services.BookService;
import com.example.demo.data.BooksPageDto;
import com.example.demo.services.BooksRatingAndPopularityService;
import com.example.demo.struct.book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api")
public class BookRestApiController {


    private final BookService bookService;
    private final BooksRatingAndPopularityService booksRatingAndPopularityService;

    @Autowired
    public BookRestApiController(BookService bookService, BooksRatingAndPopularityService booksRatingAndPopularityService) {
        this.bookService = bookService;
        this.booksRatingAndPopularityService = booksRatingAndPopularityService;
    }

    @GetMapping("/books/by-author")
    public ResponseEntity<List<BookEntity>> booksByAuthor(@RequestParam("author") String authorName) {
        return ResponseEntity.ok(bookService.getBooksByAuthorEntityName(authorName));
    }

    @GetMapping("/books/by-title")
    public ResponseEntity<List<BookEntity>> booksByTitle(@RequestParam("title") String title) {
        return ResponseEntity.ok(bookService.getBooksByTitle(title));
    }

    @GetMapping("/books/by-price-range")
    public ResponseEntity<List<BookEntity>> priceRangeBooks(@RequestParam("min") Integer min,
                                                            @RequestParam("max") Integer max) {
        return ResponseEntity.ok(bookService.getBooksWithPriceBetween(min, max));
    }

    @GetMapping("/books/with-max-discount")
    public ResponseEntity<List<BookEntity>> maxDiscountBooks() {
        return ResponseEntity.ok(bookService.getBooksWithMaxDiscount());
    }

    @GetMapping("/books/bestsellers")
    public ResponseEntity<List<BookEntity>> bestSellerBooks() {
        return ResponseEntity.ok(bookService.getBestsellers());
    }

    @GetMapping("/books/recent")
    public BooksPageDto getPageOfRecentBooks(@RequestParam("from") String from,
                                             @RequestParam("to") String to,
                                             @RequestParam("offset") Integer offset,
                                             @RequestParam("limit") Integer limit) {
        return bookService.getPageOfBetweenDatesBooks(offset, limit, from, to);
    }


    @GetMapping("/books/popular")
    public BooksPageDto getPageOfPopularBooks(@RequestParam("offset") Integer offset,
                                              @RequestParam("limit") Integer limit) {
        return new BooksPageDto(booksRatingAndPopularityService.getPageOfPopularBooks(offset, limit).getContent());
    }

}
