package com.example.shopping.dao.dto.request;

import com.example.shopping.dao.entity.LoginDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublisherRequestDto {
    String name;
    String address;
    String contactNumber;
    LoginRequestDto loginDetails;
}
