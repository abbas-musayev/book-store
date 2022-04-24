package com.example.shopping.dao.repo;

import com.example.shopping.dao.entity.LoginDetailsEnt;
import com.example.shopping.dao.entity.RoleEnt;
import com.example.shopping.dao.entity.UserEnt;
import com.example.shopping.enums.RolesEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class UserRepoTest {

    @Autowired
    UserRepo userRepo;

    @BeforeEach
    public void before() {
        List<UserEnt> list = Arrays.asList(new UserEnt(
                "abbas", "musayev", "email", "phone",
                new LoginDetailsEnt(0L, "abbas", "123456", "1", Arrays.asList(new RoleEnt(0L, RolesEnum.USER, "1"))), "1"));

        List<UserEnt> userEnts = userRepo.saveAll(list);

    }

    @Test
    void findUserEntByUsername() {
        Optional<UserEnt> optional = userRepo.findUserEntByUsername("abbas");
        int i =0;
        if (optional.isPresent()){
            i=1;
        }
        assertEquals(1,i);
    }

    @Test
    void findByUsername() {
    }

    @Test
    void saveAll() {
        List<UserEnt> list = Arrays.asList(new UserEnt(
                "abbas", "musayev", "email", "phone",
                new LoginDetailsEnt(0L, "abbas", "123456", "1", Arrays.asList(new RoleEnt(0L, RolesEnum.USER, "1"))), "1"));

        List<UserEnt> userEnts = userRepo.saveAll(list);
        AtomicInteger validId = new AtomicInteger();
        userEnts.forEach(item-> {
            if(item.getId()>0){
                validId.getAndIncrement();
            }
        });

        System.out.println(validId.intValue());

        assertEquals(1,validId.intValue());


    }
}