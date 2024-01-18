package com.example.crud.controller;

import com.example.crud.domain.UserReqDto;
import com.example.crud.domain.UserResDto;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.lang.reflect.Member;
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
    public String userCreate(@RequestBody UserReqDto userReqDto) {
        userService.userCreate(userReqDto);
        return "가입 완료";
    }

//    회원 목록 조회
    @GetMapping("/list")
    public List<UserResDto> userList() {
        return userService.userList();
    }

//    회원 상세 조회
    @GetMapping("/info/{id}")
    public UserResDto userInfo(@PathVariable int id) {
        return userService.userInfo(id);
    }

//    회원 삭제
    @DeleteMapping("/delete/{id}")
    public String userDelete(@PathVariable int id) {
        userService.userDelete(id);
        return "삭제 완료";
    }

//    회원 수정
    @PatchMapping("/update/{id}")
    public UserResDto userUpdate(@PathVariable int id, @RequestBody UserReqDto userReqDto) {
        userService.userUpdate(id, userReqDto);
        return userService.userInfo(id);
    }

}
