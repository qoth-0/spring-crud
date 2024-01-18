package com.example.crud.domain;

import lombok.Data;

@Data
public class UserReqDto {
    private String name;
    private String email;
    private String password;
}
