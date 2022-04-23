package com.example.shopping.service;

import com.example.shopping.dao.dto.request.UserRequestDto;
import com.example.shopping.dao.dto.response.UserResponseDto;
import com.example.shopping.exception.CustomNotFoundException;

import java.util.List;

public interface IUserService {

    String register(UserRequestDto dto) throws CustomNotFoundException;

    List<UserResponseDto> getAll();

    UserResponseDto getById(Long id) throws CustomNotFoundException;

    String isActive(String username) throws CustomNotFoundException;

    String delete(String username);


}
