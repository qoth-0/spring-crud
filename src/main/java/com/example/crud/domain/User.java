package com.example.crud.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime create_time;
}
