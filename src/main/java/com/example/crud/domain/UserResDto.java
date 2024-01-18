package com.example.crud.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResDto {
    private String name;
    private String email;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
}
