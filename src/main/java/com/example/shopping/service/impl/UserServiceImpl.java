package com.example.shopping.service.impl;

import com.example.shopping.config.EncryptPassword;
import com.example.shopping.dao.dto.request.UserRequestDto;
import com.example.shopping.dao.dto.response.UserResponseDto;
import com.example.shopping.dao.entity.LoginDetailsEnt;
import com.example.shopping.dao.entity.RoleEnt;
import com.example.shopping.dao.entity.UserEnt;
import com.example.shopping.dao.repo.UserRepo;
import com.example.shopping.enums.RolesEnum;
import com.example.shopping.exception.CustomNotFoundException;
import com.example.shopping.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {

    private final UserRepo userRepo;
    private final ModelMapper mapper;

    @Override
    public String register(UserRequestDto entity) {

        LoginDetailsEnt loginDetailsEnt = mapper.map(entity.getLoginDetails(), LoginDetailsEnt.class);

        Optional<UserEnt> user = userRepo.findByUsername(loginDetailsEnt.getUsername());

        if (user.isPresent() && user.equals("1")) {
            throw new CustomNotFoundException("This username  already exists");
        }

        UserEnt map = mapper.map(entity, UserEnt.class);
        String encrypt = EncryptPassword.hashPassword(loginDetailsEnt.getPassword());
        loginDetailsEnt.setPassword(encrypt);
        loginDetailsEnt.setIsActive("1");
        loginDetailsEnt.setRoles(Collections.singletonList(new RoleEnt(null, RolesEnum.USER,"1")));
        map.setLoginDetailsEnt(loginDetailsEnt);
        map.setIsActive("1");

        userRepo.save(map);
        return "Saved";
    }

    @Override
    public UserResponseDto getById(Long id) throws CustomNotFoundException {
        UserEnt item = userRepo.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("User Not Found"));

        UserResponseDto user = new UserResponseDto();
        user.setName(item.getName());
        user.setSurname(item.getSurname());
        user.setUsername(item.getLoginDetailsEnt().getUsername());
        user.setEmail(item.getEmail());
        user.setPhone(item.getPhone());
        return user;
    }

    @Override
    public List<UserResponseDto> getAll() {
        List<UserEnt> all = userRepo.findAll();
        List<UserResponseDto> response = new ArrayList<>();
        for (UserEnt item : all) {
            UserResponseDto user = new UserResponseDto();
            user.setName(item.getName());
            user.setSurname(item.getSurname());
            user.setUsername(item.getLoginDetailsEnt().getUsername());
            user.setEmail(item.getEmail());
            user.setPhone(item.getPhone());
            response.add(user);
        }
        return response;
    }

//    @Override
//    public Boolean loginUser(String username, String password) {
//        Boolean aBoolean = userRepo.loginUser(username, password);
//        return aBoolean;
//    }

    @Override
    public String isActive(String username) throws CustomNotFoundException {
        UserEnt userByUsername = userRepo.findUserEntByUsername(username)
                .orElseThrow(() -> new CustomNotFoundException("Username Not Found"));
        return userByUsername.getIsActive();
    }

    @Override
    public String delete(String username) {
        UserEnt user = userRepo.findUserEntByUsername(username)
                .orElseThrow(() -> new CustomNotFoundException("Username Not Found"));
        user.getLoginDetailsEnt().setIsActive("1");
        userRepo.save(user);
        return "User Deleted Success";
    }


}
