package com.example.shopping.dao.dto.request;

import com.example.shopping.enums.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRequestDTO {

    String name;
    String price;
    String description;
    String publishDate;
    List<String> authors;
    List<PublisherDto> publishers;
    Genre genre;
}
