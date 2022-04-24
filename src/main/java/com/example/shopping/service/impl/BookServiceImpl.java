package com.example.shopping.service.impl;

import com.example.shopping.dao.dto.request.BookRequestDTO;
import com.example.shopping.dao.dto.response.BookResponseDTO;
import com.example.shopping.dao.entity.AuthorEnt;
import com.example.shopping.dao.entity.BookEnt;
import com.example.shopping.dao.repo.BookRepo;
import com.example.shopping.service.IBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@Slf4j
public class BookServiceImpl implements IBookService {

    private final BookRepo bookRepo;
    private final ModelMapper mapper;

    @Autowired
    public BookServiceImpl(BookRepo bookRepo, ModelMapper mapper) {
        this.bookRepo = bookRepo;
        this.mapper = mapper;
    }

//    public BookServiceImpl(BookRepo bookRepo) {
//        this.bookRepo = bookRepo;
//    }

    @Override
    public String save(BookRequestDTO entity) {

        BookEnt map = mapper.map(entity, BookEnt.class);

        List<AuthorEnt> authorEnts = new ArrayList<>();
        for (String author : entity.getAuthors()) {
            authorEnts.add(new AuthorEnt(author, "1"));
        }
        log.info("Pulisher ;{}", map.getAuthor());

        map.setAuthor(authorEnts);
        map.setIsActive("1");
        bookRepo.save(map);
        return "Saved";
    }

    @Override
    public List<BookResponseDTO> viewAllBooks(Integer pageNumber, Integer pageSize) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);

        Page<BookEnt> all = bookRepo.findAll(pageable);
        List<BookEnt> content = all.getContent();
        List<BookResponseDTO> dtos = new ArrayList<>();
        content.forEach(item -> {
            BookResponseDTO map = mapper.map(item, BookResponseDTO.class);
            dtos.add(map);
        });

        return dtos;
//        return bookRepo.viewAllBooks();
    }

    @Override
    public List<BookResponseDTO> viewAllBooksByPublisherName(String name) {
        return bookRepo.getAllBooksByPublisherName(name);
    }

    @Override
    public List<BookResponseDTO> getBooksByAuthorName(String author) {
        return bookRepo.getBooksByAuthorName(author);
    }

    @Override
    public List<BookResponseDTO> getBookByName(String bookName) {
        List<BookResponseDTO> allBookByName = bookRepo.getAllBookByName(bookName);

        return allBookByName;
    }
}
