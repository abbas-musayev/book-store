package com.example.shopping.service.impl;

import com.example.shopping.dao.dto.request.LoginRequestDto;
import com.example.shopping.dao.dto.request.UserRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Autowired
    private UserServiceImpl userService;



    @Test
    void save() {

        UserRequestDto dto = new UserRequestDto();
        dto.setName("ABbas");
        dto.setEmail("abbas@mail.ru");
        dto.setPhone("121212");
        dto.setSurname("Musyaev");
        dto.setLoginDetails(new LoginRequestDto("abbas","123456"));

        String register = userService.register(dto);

        assertEquals("Saved",register);
    }
}