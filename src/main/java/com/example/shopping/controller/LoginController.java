package com.example.shopping.controller;

import com.example.shopping.config.EncryptPassword;
import com.example.shopping.dao.dto.request.AuthRequestDto;
import com.example.shopping.dao.dto.response.AuthResponseDto;
import com.example.shopping.dao.entity.LoginDetails;
import com.example.shopping.dao.repo.LoginDetailsRepo;
import com.example.shopping.exception.CustomNotFoundException;
import com.example.shopping.security.TokenManager;
import com.example.shopping.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final IUserService userService;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginDetailsRepo repo;

    @PostMapping("/auth")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto request) throws CustomNotFoundException {
        log.info("----------Login Controller-------");

        Optional<LoginDetails> entity = repo.findLoginEntByUsername(request.getUsername());

        if (entity.get().getIsActive().equals("0")){
            throw new RuntimeException("USER'S ACCOUNT DOES NOT EXIST");
        }

        log.info("-------------------------"+entity.get());
        if (!EncryptPassword.checkPass(request.getPassword(),entity.get().getPassword())){
            throw new BadCredentialsException("Password is Incorrect");
        }

        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            String authorities = authenticate.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));
            String token = tokenManager.generateToken(request.getUsername(), authorities);
            System.out.println("////////////////////////AUth Controller//////////////////////////////");
            return ResponseEntity.ok( new AuthResponseDto("success", token));
        } catch (Exception e) {
            throw e;
        }
    }
}
