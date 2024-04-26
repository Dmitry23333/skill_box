package com.example.service.integration.mapper;

import com.example.service.integration.entity.Book;
import com.example.service.integration.web.model.request.UpsertBookRequest;
import com.example.service.integration.web.model.response.BookResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-26T13:45:55+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class BookMapperImpl_ implements BookMapper {

    @Override
    public Book requestToBook(UpsertBookRequest request) {
        if ( request == null ) {
            return null;
        }

        Book book = new Book();

        book.setAuthor( request.getAuthor() );
        book.setTitle( request.getTitle() );

        return book;
    }

    @Override
    public Book requestToBook(UUID bookId, UpsertBookRequest request) {
        if ( bookId == null && request == null ) {
            return null;
        }

        Book book = new Book();

        if ( request != null ) {
            book.setAuthor( request.getAuthor() );
            book.setTitle( request.getTitle() );
        }
        book.setId( bookId );

        return book;
    }

    @Override
    public BookResponse bookToResponse(Book book) {
        if ( book == null ) {
            return null;
        }

        BookResponse bookResponse = new BookResponse();

        return bookResponse;
    }

    @Override
    public List<BookResponse> bookListToResponseList(List<Book> books) {
        if ( books == null ) {
            return null;
        }

        List<BookResponse> list = new ArrayList<BookResponse>( books.size() );
        for ( Book book : books ) {
            list.add( bookToResponse( book ) );
        }

        return list;
    }
}
