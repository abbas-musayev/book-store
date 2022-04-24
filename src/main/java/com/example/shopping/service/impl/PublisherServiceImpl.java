package com.example.shopping.service.impl;

import com.example.shopping.config.EncryptPassword;
import com.example.shopping.dao.dto.request.PublisherRequestDto;
import com.example.shopping.dao.entity.LoginDetailsEnt;
import com.example.shopping.dao.entity.PublisherEnt;
import com.example.shopping.dao.entity.RoleEnt;
import com.example.shopping.dao.repo.PublisherRepo;
import com.example.shopping.enums.RolesEnum;
import com.example.shopping.exception.CustomNotFoundException;
import com.example.shopping.service.IPublisherService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements IPublisherService {

    private final PublisherRepo publisherRepo;
    private final ModelMapper mapper;

    @Override
    public String register(PublisherRequestDto dto) {

        LoginDetailsEnt loginDetailsEnt = mapper.map(dto.getLoginDetails(), LoginDetailsEnt.class);

        Optional<PublisherEnt> optional = publisherRepo.getPublisherByName(loginDetailsEnt.getUsername());

        if (optional.isPresent() && optional.get().getIsActive().equals("1")){
            throw new CustomNotFoundException("This username  already exists");
        }
        PublisherEnt publisher = mapper.map(dto, PublisherEnt.class);

        String encrypt = EncryptPassword.hashPassword(loginDetailsEnt.getPassword());
        loginDetailsEnt.setPassword(encrypt);
        loginDetailsEnt.setIsActive("1");
        loginDetailsEnt.setRoles(Collections.singletonList(new RoleEnt(null, RolesEnum.PUBLISHER,"1")));
        publisher.setLoginDetailsEnt(loginDetailsEnt);
        publisher.setIsActive("1");
        publisherRepo.save(publisher);
        return "Saved";
    }
}
