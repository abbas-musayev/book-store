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
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class LoginDetailsEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String username;
    String password;

    public LoginDetailsEnt(Long id, String username, String password, String isActive, List<RoleEnt> roleEntList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roleEntList;
        this.isActive=isActive;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")})
    List<RoleEnt> roles;

    @CreationTimestamp
    LocalDateTime createdate;
    @CreationTimestamp
    LocalDateTime updateDate;
    String isActive;

}
