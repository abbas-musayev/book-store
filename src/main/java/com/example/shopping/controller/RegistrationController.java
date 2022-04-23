package com.example.shopping.controller;

import com.example.shopping.dao.dto.request.PublisherRequestDto;
import com.example.shopping.dao.dto.request.UserRequestDto;
import com.example.shopping.service.IPublisherService;
import com.example.shopping.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final IUserService userService;

    private final IPublisherService publisherService;

    @PostMapping("/user")
    public ResponseEntity<String> registerUser(@RequestBody UserRequestDto request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/publisher")
    public ResponseEntity<String> registerPublisher(@RequestBody PublisherRequestDto request) {
        return ResponseEntity.ok(publisherService.register(request));
    }
}
