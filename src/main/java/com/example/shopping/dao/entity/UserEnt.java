package com.example.shopping.dao.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class UserEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;

    @Email(message = "Please write valid email")
    String email;
    String phone;
    @OneToOne(cascade = CascadeType.ALL)
    LoginDetailsEnt loginDetailsEnt;

    @CreationTimestamp
    LocalDateTime createdate;
    @CreationTimestamp
    LocalDateTime updateDate;
    String isActive;

    public UserEnt(String name, String surname, String email, String phone, LoginDetailsEnt loginDetailsEnt, String isActive) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.loginDetailsEnt = loginDetailsEnt;
        this.isActive = isActive;
    }


    //    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "USER_ROLES", joinColumns = {
//            @JoinColumn(name = "USER_ID")}, inverseJoinColumns = {
//            @JoinColumn(name = "ROLE_ID")})
//    List<RoleEnt> roles;
}
