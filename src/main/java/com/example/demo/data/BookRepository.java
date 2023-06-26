package com.example.demo.data;

import com.example.demo.struct.book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;


import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    Page<BookEntity> findBookByTitleContaining(String bootTitle, Pageable nextPage);

    List <BookEntity> findBooksByAuthorEntityNameContaining(String name);

    List <BookEntity> findBooksByTitle(String bookTitle);

    List <BookEntity> findBooksByPriceBetween(Integer min, Integer max);
    List <BookEntity> findBooksByPriceIs(Integer price);

    @Query("from BookEntity  where  isBestseller =1")
    List <BookEntity> getBestsellers();


    @Query(value = "SELECT * FROM  book WHERE discount = (SELECT MAX (discount) FROM book)", nativeQuery = true)
    List<BookEntity> getBooksWithMaxDiscount();

    @Query(value = "SELECT * FROM  book ORDER BY pub_date DESC ", nativeQuery = true)
    Page<BookEntity> getRecent(Pageable nextPage);


    @Query(value = "SELECT * FROM  book ORDER BY pub_date DESC ", nativeQuery = true)
    List<BookEntity> getRestRecent();

    Page <BookEntity> findBookEntityByPubDateBetween(Date pubDate, Date pubDate2, Pageable pageable);
    //List <BookEntity> findBookEntityByPubDateBetween(Date pubDate, Date pubDate2);
}
