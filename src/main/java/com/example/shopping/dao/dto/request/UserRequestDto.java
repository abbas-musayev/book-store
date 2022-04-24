package com.example.shopping.dao.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
