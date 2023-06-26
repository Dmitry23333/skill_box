package com.example.demo.data;

import com.example.demo.struct.book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> getBooksData() {
        return bookRepository.findAll();
    }

    public Page<BookEntity> getPageOfRecommendedBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }


    public Page<BookEntity> getPageOfRecentBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getRecent(nextPage);
    }


    public Page<BookEntity> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBookByTitleContaining(searchWord, nextPage);
    }


    public Page<BookEntity> getPageOfSearchDateBetweenResultBooks(String from, String to, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
           Date dateStart = formatter.parse(from);
            Date dateEnd = formatter.parse(to);
    return bookRepository.findBookEntityByPubDateBetween(dateStart,dateEnd, nextPage);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public List<BookEntity> getBooksByAuthorEntityName(String name) {
        return bookRepository.findBooksByAuthorEntityNameContaining(name);
    }

    public List<BookEntity> getBooksByTitle(String title) {
        return bookRepository.findBooksByTitle(title);
    }


    public List<BookEntity> getBooksWithPriceBetween(Integer min, Integer max) {
        return bookRepository.findBooksByPriceBetween(min, max);
    }

    public List<BookEntity> getBooksWithPrice(Integer price) {
        return bookRepository.findBooksByPriceIs(price);
    }


    public List<BookEntity> getBooksWithMaxDiscount() {
        return bookRepository.getBooksWithMaxDiscount();
    }

    public List<BookEntity> getBestsellers() {
        return bookRepository.getBestsellers();
    }

   // public List<BookEntity> getRestRecentBooks(String from, String to) {
    //    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
   //     try {
    //        Date dateStart = formatter.parse(from);
    //        Date dateEnd = formatter.parse(to);
    //        return bookRepository.findBookEntityByPubDateBetweenIs(dateStart,dateEnd);
    //    } catch (ParseException e) {
    //        throw new RuntimeException(e);
    //    }
   // }


}
