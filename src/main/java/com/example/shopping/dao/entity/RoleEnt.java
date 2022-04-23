package com.example.shopping.dao.entity;

import com.example.shopping.enums.RolesEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class RoleEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    RolesEnum name;

    public RoleEnt(Long id, RolesEnum name,String isActive) {
        this.id = id;
        this.name = name;
        this.isActive=isActive;
    }

    String isActive;

    @CreationTimestamp
    LocalDateTime createdate;
    @CreationTimestamp
    LocalDateTime updateDate;

}
