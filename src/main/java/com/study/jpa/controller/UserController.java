package com.study.jpa.controller;

import com.study.jpa.dto.UserLoginDto;
import com.study.jpa.entity.TokenInfo;
import com.study.jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
