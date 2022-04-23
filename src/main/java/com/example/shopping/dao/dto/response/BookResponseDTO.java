package com.example.shopping.dao.dto.response;

import com.example.shopping.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookResponseDTO {
    String name;
    String description;
    Genre genre;
    String publishDate;
    String price;
    String author;

    public BookResponseDTO(String name, String description, Genre genre, String publishDate, String price, String author) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.publishDate = publishDate;
        this.price = price;
        this.author = author;
    }
}
