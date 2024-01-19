package com.example.crud.controller;

import com.example.crud.domain.UserReqDto;
import com.example.crud.domain.UserResDto;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.websocket.server.PathParam;
import java.lang.reflect.Member;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    회원가입
    @PostMapping("/create")
    public ResponseEntity<String> userCreate(@RequestBody UserReqDto userReqDto) {
        try {
            userService.userCreate(userReqDto);
            return new ResponseEntity<>(userReqDto.getName()+"님, 환영합니다.", HttpStatus.CREATED);
        }catch (DataIntegrityViolationException e) { // 메일 중복 시
            e.printStackTrace();
            return new ResponseEntity<>("중복된 이메일입니다.", HttpStatus.CONFLICT);
        }
    }

//    회원 목록 조회
    @GetMapping("/list")
    public List<UserResDto> userList() {
        return userService.userList();
    }

////    회원 상세 조회
//    @GetMapping("/info/{id}")
//    public ResponseEntity<UserResDto> userInfo(@PathVariable int id) { // id 없을 때 에러
//        try {
//            return new ResponseEntity<>(userService.userInfo(id), HttpStatus.OK);
//        }catch (EntityNotFoundException e) { // 존재하지 않을 경우 404
//            e.printStackTrace();
//            return ResponseEntityController.failed(HttpStatus.NOT_FOUND, e.getMessage());
//        }
//    }

//    회원 삭제
    @DeleteMapping("/delete/{id}")
    public String userDelete(@PathVariable int id) { // id 없을 때 에러
        userService.userDelete(id);
        return "삭제 완료";
    }

//    회원 수정
    @PatchMapping("/update/{id}")
    public UserResDto userUpdate(@PathVariable int id, @RequestBody UserReqDto userReqDto) { // id 없을 때 에러
        userService.userUpdate(id, userReqDto);
        return userService.userInfo(id);
    }

}
