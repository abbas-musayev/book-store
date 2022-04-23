package com.example.shopping.dao.repo;

import com.example.shopping.dao.dto.response.BookResponseDTO;
import com.example.shopping.dao.entity.BookEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<BookEnt, Long> {

    @Query("select new com.example.shopping.dao.dto.response.BookResponseDTO(" +
            "book.name," +
            "book.description," +
            "book.genre," +
            "book.publishDate," +
            "book.price," +
            "author.fullName)" +
            " from BookEnt book join book.author author where author.fullName like %?1%")
    List<BookResponseDTO> getBooksByAuthorName(String username);

    @Query("select new com.example.shopping.dao.dto.response.BookResponseDTO(" +
            "book.name," +
            "book.description," +
            "book.genre," +
            "book.publishDate," +
            "book.price," +
            "author.fullName)" +
            " from BookEnt book join book.author author where book.name like CONCAT('%', :name, '%') and book.isActive='1'")
    List<BookResponseDTO> getAllBookByName(@Param("name") String name);


    @Query("select new com.example.shopping.dao.dto.response.BookResponseDTO(" +
            "book.name," +
            "book.description," +
            "book.genre," +
            "book.publishDate, " +
            "book.price, " +
            "author.fullName)" +
            " from BookEnt book join book.author author")
    List<BookResponseDTO> viewAllBooks();


    @Query("select new com.example.shopping.dao.dto.response.BookResponseDTO(" +
            "book.name," +
            "book.description," +
            "book.genre," +
            "book.publishDate, " +
            "book.price, " +
            "publisher.name)" +
            " from BookEnt book join book.publishers publisher where publisher.name like %?1%")
    List<BookResponseDTO> getAllBooksByPublisherName(String name);
}
