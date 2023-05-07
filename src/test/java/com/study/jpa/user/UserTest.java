package com.study.jpa.user;

import com.study.jpa.dto.UserLoginDto;
import com.study.jpa.service.CustomUserDetailsService;
import com.study.jpa.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback(false)
public class UserTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @DisplayName("login 테스트")
    @Test
    void loginTest(){
        String testUrl = "http://localhost:"+port+"/user/login";

        UserLoginDto userLoginDto = new UserLoginDto("dudtkd0219@gmail.com","1234");

        ResponseEntity<Object> response = restTemplate.postForEntity(testUrl, userLoginDto, Object.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println(response.getBody());
    }
    
    @DisplayName("회원 가입 테스트")
    @Test
    void joinTest(){
        String testUrl = "http://localhost:"+port+"/user/join";

        UserLoginDto userLoginDto = new UserLoginDto("test01","1234");

        ResponseEntity<String> response = restTemplate.postForEntity(testUrl, userLoginDto,String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        UserDetails user = customUserDetailsService.loadUserByUsername(userLoginDto.getUserEmail());

        assertThat(user.getUsername()).isEqualTo(userLoginDto.getUserEmail());
    }
}
