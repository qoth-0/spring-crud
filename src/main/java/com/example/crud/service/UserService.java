package com.example.crud.service;

import com.example.crud.domain.UserReqDto;
import com.example.crud.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    회원 등록
    public void userCreate(UserReqDto userReqDto) {

    }

//    회원 목록
    public void users() {

    }
}
