package com.example.shopping.dao.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@Embeddable
public class CoreEnt {

    @CreationTimestamp
    LocalDateTime createdate;
    @CreationTimestamp
    LocalDateTime updateDate;
    String isActive;
}



