package com.example.crud.service;

import com.example.crud.domain.User;
import com.example.crud.domain.UserReqDto;
import com.example.crud.domain.UserResDto;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    회원 가입
    public void userCreate(UserReqDto userReqDto) {
        User user = new User(userReqDto.getName(), userReqDto.getEmail(), userReqDto.getPassword());
        userRepository.save(user);
    }


}
