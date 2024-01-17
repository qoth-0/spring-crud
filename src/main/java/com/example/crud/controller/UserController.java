package com.example.crud.controller;

import com.example.crud.domain.UserReqDto;
import com.example.crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //    홈화면
    @GetMapping("/")
    public String home() {
        return "home";
    }

//    회원가입
    @GetMapping("/users/join")
    public String joinScreen() {
        return "user/user-join";
    }
    @PostMapping("/users/join")
    @ResponseBody
    public String join(UserReqDto userReqDto) { // 데이터 바인딩
        userService.userCreate(userReqDto);
        return "";
    }

//    회원 목록 조회
    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userService.users());
        return "user/user-list";
    }

}
