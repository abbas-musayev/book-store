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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements IBookService {

    private final BookRepo bookRepo;
    private final ModelMapper mapper;

    @Override
    public String save(BookRequestDTO entity) {

        BookEnt map = mapper.map(entity, BookEnt.class);

        List<AuthorEnt> authorEnts = new ArrayList<>();
        for (String author : entity.getAuthors()) {
            authorEnts.add(new AuthorEnt(author,"1"));
        }
        log.info("Pulisher ;{}",map.getAuthor());

        map.setAuthor(authorEnts);
        map.setIsActive("1");
        bookRepo.save(map);
        return "Saved";
    }

    @Override
    public List<BookResponseDTO> viewAllBooks() {
        return bookRepo.viewAllBooks();
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
