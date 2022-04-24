package com.example.shopping.service;

import com.example.shopping.dao.dto.request.BookRequestDTO;
import com.example.shopping.dao.dto.response.BookResponseDTO;
import com.example.shopping.dao.entity.BookEnt;

import java.util.List;

public interface IBookService {

    String save(BookRequestDTO entity);

    List<BookResponseDTO> viewAllBooks(Integer pageNumber,Integer pageSize);

    List<BookResponseDTO> viewAllBooksByPublisherName(String name);

    List<BookResponseDTO> getBooksByAuthorName(String author);

    List<BookResponseDTO> getBookByName(String bookName);
}
