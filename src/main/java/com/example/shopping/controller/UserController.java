package com.example.shopping.controller;

import com.example.shopping.dao.dto.response.UserResponseDto;
import com.example.shopping.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/id")
    public ResponseEntity<UserResponseDto> getById(@RequestParam String id){
        return ResponseEntity.ok(userService.getById(Long.valueOf(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping()
//    public ResponseEntity<String> deleteUser(@RequestParam String username) {
//        return ResponseEntity.ok(userService.delete(username));
//    }

}
