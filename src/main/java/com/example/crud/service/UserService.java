package com.example.crud.service;

import com.example.crud.domain.User;
import com.example.crud.domain.UserReqDto;
import com.example.crud.domain.UserResDto;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
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
    public void userCreate(UserReqDto userReqDto) throws SQLIntegrityConstraintViolationException {
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
            userResDto.setUpdate_time(user.getUpdate_time());
            userResDtos.add(userResDto);
        }
        return userResDtos;
    }

//    회원 상세
    public UserResDto userInfo(int id) throws EntityNotFoundException{ // Controller로 예외 던지기
//        findById는 Optional을 반환하므로 orElseThrow로 null일 경우 예외처리 - DB에 존재하지 않을 경우
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
        UserResDto userResDto = new UserResDto();
        userResDto.setName(user.getName());
        userResDto.setEmail(user.getEmail());
        userResDto.setCreate_time(user.getCreate_time());
        userResDto.setUpdate_time(user.getUpdate_time());
        return userResDto;
    }

//    회원 삭제
    public void userDelete(int id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userRepository.delete(user);
    }

//    회원 수정
    public void userUpdate(int id, UserReqDto userReqDto) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.userUpdate(userReqDto.getName(), userReqDto.getEmail(), userReqDto.getPassword());
        userRepository.save(user);
    }

}
