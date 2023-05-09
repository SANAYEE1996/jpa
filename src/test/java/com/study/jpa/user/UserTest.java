package com.study.jpa.user;

import com.study.jpa.dto.UserLoginDto;
import com.study.jpa.service.CustomUserDetailsService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.StringUtils;


import java.util.HashMap;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback(false)
public class UserTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @DisplayName("login 테스트")
    @Test
    void loginTest() throws JSONException {
        String testUrl = "http://localhost:"+port+"/user/login";

        UserLoginDto userLoginDto = new UserLoginDto("test01","1234");

        ResponseEntity<String> response = restTemplate.postForEntity(testUrl, userLoginDto, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        JSONObject jsonObject = new JSONObject(response.getBody());
        assertThat(jsonObject.getString("grantType")).isEqualTo("Bearer");
        assertThat(StringUtils.hasText(jsonObject.getString("accessToken"))).isTrue();
        assertThat(StringUtils.hasText(jsonObject.getString("refreshToken"))).isTrue();

        System.out.println(jsonObject.getString("accessToken"));
        System.out.println(jsonObject.getString("refreshToken"));
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

    @DisplayName("로그인 하고 발급된 bearer token으로 다른 api 접속 가능한지 테스트")
    @Test
    void LoginAndAccessAuthenticatedAPITest(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getToken());
        String testUrl = "http://localhost:"+port+"/user/test";

        ResponseEntity<String> response = restTemplate.exchange(testUrl, HttpMethod.GET, new HttpEntity<>(headers), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("success");
    }

    private String getToken(){
        String loginUrl = "http://localhost:"+port+"/user/login";

        UserLoginDto userLoginDto = new UserLoginDto("test01","1234");

        ResponseEntity<HashMap> response = restTemplate.postForEntity(loginUrl, userLoginDto, HashMap.class);

        return Objects.requireNonNull(response.getBody()).get("accessToken").toString();
    }
}
