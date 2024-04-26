package com.example.service.integration.mapper;

import com.example.service.integration.entity.Book;
import com.example.service.integration.web.model.request.UpsertBookRequest;
import com.example.service.integration.web.model.response.BookListResponse;
import com.example.service.integration.web.model.response.BookResponse;

import org.mapstruct.*;


import java.util.List;
import java.util.UUID;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@DecoratedWith(BookMapperDelegate.class)
public interface BookMapper {

    Book requestToBook(UpsertBookRequest request);

    @Mapping(source = "bookId", target = "id")
    Book requestToBook(UUID bookId, UpsertBookRequest request);

    BookResponse bookToResponse(Book book);

    List<BookResponse> bookListToResponseList(List<Book> books);

    default BookListResponse booksToBooksResponseList(List<Book> books) {
        BookListResponse response = new BookListResponse();
        response.setBooks(bookListToResponseList(books));
        return response;
    }
}
