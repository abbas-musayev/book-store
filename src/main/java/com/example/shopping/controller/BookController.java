package com.example.shopping.controller;

import com.example.shopping.dao.dto.request.BookRequestDTO;
import com.example.shopping.dao.dto.response.BookResponseDTO;
import com.example.shopping.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @PreAuthorize("hasRole('ROLE_PUBLISHER')")
    @PostMapping
    public ResponseEntity<String> save(@RequestBody BookRequestDTO request) {
        System.out.println("BookController");
        return ResponseEntity.ok(bookService.save(request));
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_PUBLISHER')")
    @GetMapping("/publisher-books")
    public ResponseEntity<List<BookResponseDTO>> viewAllBooksByPublisherName(@RequestParam String name) {
        return ResponseEntity.ok(bookService.viewAllBooksByPublisherName(name));
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_PUBLISHER')")
    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> viewAllBooks() {
        return ResponseEntity.ok(bookService.viewAllBooks());
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_PUBLISHER')")
    @GetMapping("/getByAuthorname")
    public ResponseEntity<List<BookResponseDTO>> getBooksByAuthotName(@RequestParam String name) {
        return ResponseEntity.ok(bookService.getBooksByAuthorName(name));
    }


    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_PUBLISHER')")
    @GetMapping("/getByName")
    public ResponseEntity<List<BookResponseDTO>> getBookByName(@RequestParam String name) {
        return ResponseEntity.ok(bookService.getBookByName(name));
    }


}
