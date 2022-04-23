package com.example.shopping.dao.dto.request;

import com.example.shopping.dao.entity.LoginDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {

    String name;
    String surname;
    String email;
    String phone;
    LoginRequestDto loginDetails;
}
