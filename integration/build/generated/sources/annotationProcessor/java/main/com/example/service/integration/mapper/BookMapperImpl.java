package com.example.service.integration.mapper;

import com.example.service.integration.entity.Book;
import com.example.service.integration.web.model.response.BookResponse;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-26T13:45:55+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
@Primary
public class BookMapperImpl extends BookMapperDelegate {

    @Autowired
    @Qualifier("delegate")
    private BookMapper delegate;

    @Override
    public BookResponse bookToResponse(Book book)  {
        return delegate.bookToResponse( book );
    }

    @Override
    public List<BookResponse> bookListToResponseList(List<Book> books)  {
        return delegate.bookListToResponseList( books );
    }
}
