package com.example.shopping.dao.entity;

import com.example.shopping.enums.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class BookEnt
//        extends CoreEnt
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String price;
    String description;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    String publishDate;

//    Boolean isNew;

    @ManyToMany(cascade = CascadeType.ALL)
    List<AuthorEnt> author;

    @ManyToMany()
    List<PublisherEnt> publishers;

    //    @ElementCollection(targetClass = Genre.class)
//    @CollectionTable
    @Enumerated(EnumType.STRING)
    Genre genre;

    @CreationTimestamp
    LocalDateTime createdate;
    @CreationTimestamp
    LocalDateTime updateDate;
    String isActive;
}
