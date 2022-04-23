package com.example.shopping.service.impl;

import com.example.shopping.config.EncryptPassword;
import com.example.shopping.dao.dto.request.PublisherRequestDto;
import com.example.shopping.dao.entity.LoginDetails;
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

        LoginDetails loginDetails = mapper.map(dto.getLoginDetails(),LoginDetails.class);

        Optional<PublisherEnt> optional = publisherRepo.getPublisherByName(loginDetails.getUsername());

        if (optional.isPresent() && optional.get().getIsActive().equals("1")){
            throw new CustomNotFoundException("This username  already exists");
        }
        PublisherEnt publisher = mapper.map(dto, PublisherEnt.class);

        String encrypt = EncryptPassword.hashPassword(loginDetails.getPassword());
        loginDetails.setPassword(encrypt);
        loginDetails.setIsActive("1");
        loginDetails.setRoles(Collections.singletonList(new RoleEnt(null, RolesEnum.PUBLISHER,"1")));
        publisher.setLoginDetails(loginDetails);
        publisher.setIsActive("1");
        publisherRepo.save(publisher);
        return "Saved";
    }
}
