package com.example.shopping.security;

import com.example.shopping.config.EncryptPassword;
import com.example.shopping.dao.entity.LoginDetails;
import com.example.shopping.dao.repo.LoginDetailsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginDetailsRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("--------loadUserByUsername---------");
        LoginDetails userMy = repo.findLoginEntByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username \""+"username\""+" not found")));

            return new User(userMy.getUsername(), userMy.getPassword(),getAuthority(userMy));
    }

    private List<SimpleGrantedAuthority> getAuthority(LoginDetails user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            log.info("--------GETAUTHORITY-> ROLE:"+role.getName()+"---------");
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }
}