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

//    회원 목록
    public List<UserResDto> userList() {
        List<User> users = userRepository.findAll();
        List<UserResDto> userResDtos = new ArrayList<>();
        for(User user : users) {
            UserResDto userResDto = new UserResDto();
            userResDto.setName(user.getName());
            userResDto.setEmail(user.getEmail());
            userResDto.setCreate_time(user.getCreate_time());
            userResDtos.add(userResDto);
        }
        return userResDtos;
    }

//    회원 상세
    public UserResDto userInfo(int id) {
//        findById는 Optional을 반환하므로 orElseThrow로 null일 경우 예외처리 - DB에 존재하지 않을 경우
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        UserResDto userResDto = new UserResDto();
        userResDto.setName(user.getName());
        userResDto.setEmail(user.getEmail());
        userResDto.setCreate_time(user.getCreate_time());
        return userResDto;
    }


}
