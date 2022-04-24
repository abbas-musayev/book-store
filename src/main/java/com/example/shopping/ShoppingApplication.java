package com.example.shopping;

import com.example.shopping.dao.entity.LoginDetailsEnt;
import com.example.shopping.dao.entity.RoleEnt;
import com.example.shopping.dao.entity.UserEnt;
import com.example.shopping.dao.repo.UserRepo;
import com.example.shopping.enums.RolesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class ShoppingApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingApplication.class, args);
    }

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepo.findAll().isEmpty()) {
            String encode = encoder.encode("123456");
            UserEnt build = UserEnt.builder()
                    .name("Abbas")
                    .email("abbas99musayev@gmail.com")
                    .isActive("1")
                    .loginDetailsEnt(new LoginDetailsEnt(null, "abbas", encode, "1", Arrays.asList(
                            new RoleEnt(null, RolesEnum.USER, "1"),
                            new RoleEnt(null, RolesEnum.ADMIN, "1"),
                            new RoleEnt(null, RolesEnum.PUBLISHER, "1"))))
                    .build();
            userRepo.save(build);
        }

    }
}
