package com.example.shopping.dao.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class PublisherEnt
//        extends CoreEnt
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String address;
    String contactNumber;
    @OneToOne(cascade = CascadeType.ALL)
    LoginDetailsEnt loginDetailsEnt;
    @ManyToMany(cascade = CascadeType.ALL)
    List<BookEnt> bookEnts;

    @CreationTimestamp
    LocalDateTime createdate;
    @CreationTimestamp
    LocalDateTime updateDate;
    String isActive;
}
