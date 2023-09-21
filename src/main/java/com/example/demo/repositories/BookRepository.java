package com.example.demo.repositories;

import com.example.demo.struct.book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer>, CrudRepository<BookEntity, Integer> {

    Page<BookEntity> findBookByTitleContaining(String bootTitle, Pageable nextPage);

    List<BookEntity> findBooksByAuthorEntityNameContaining(String name);

    List<BookEntity> findBooksByTitle(String bookTitle);

    List<BookEntity> findBooksByPriceBetween(Integer min, Integer max);

    List<BookEntity> findBooksByPriceIs(Integer price);

    @Query("from BookEntity  where  isBestseller =1")
    List<BookEntity> getBestsellers();

    @Query(value = "SELECT * FROM  book WHERE discount = (SELECT MAX (discount) FROM book)", nativeQuery = true)
    List<BookEntity> getBooksWithMaxDiscount();

    @Query(value = "SELECT * FROM  book ORDER BY pub_date DESC ", nativeQuery = true)
    Page<BookEntity> getRecent(Pageable nextPage);

    @Query(value = "SELECT * FROM  Book b WHERE b.pub_date > :from and b.pub_date < :to ORDER BY b.pub_date DESC ", nativeQuery = true)
    Page<BookEntity> getBooksBetweenDates(Pageable nextPage, @Param("from") Date from, @Param("to") Date to);

    @Query(value = "SELECT * FROM  book WHERE tag_id = :id ORDER BY pub_date DESC", nativeQuery = true)
    Page<BookEntity> getBooksByTagID(Pageable nextPage, @Param("id") Integer tagId);


    @Query(value = "SELECT * FROM  book ORDER BY popularity DESC", nativeQuery = true)
    Page<BookEntity> getPopularBooks(Pageable nextPage);

    @Query(value = "SELECT * FROM  book, book2genre_entity WHERE  book2genre_entity.book_id = book.id AND book2genre_entity.genre_entities_id= :id", nativeQuery = true)
    Page<BookEntity> getBooksByGenreId(Pageable nextPage, @Param("id") Integer tagId);


    @Query(value = "SELECT * FROM  book WHERE  author_id= :id", nativeQuery = true)
    Page<BookEntity> getBooksByAuthorId(Pageable nextPage, @Param("id") Integer authorId);
}
