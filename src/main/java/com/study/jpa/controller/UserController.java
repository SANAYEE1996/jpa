package com.study.jpa.controller;

import com.study.jpa.dto.UserLoginDto;
import com.study.jpa.entity.TokenInfo;
import com.study.jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody UserLoginDto userLoginDto){
        return userService.login(userLoginDto.getUserEmail(), userLoginDto.getUserPassword());
    }

    @GetMapping("/test")
    public String test() {
        return "success";
    }

    @PostMapping("/join")
    public void join(@RequestBody UserLoginDto userLoginDto){
        userService.saveUser(userLoginDto.getUserEmail(), userLoginDto.getUserPassword());
    }
}
