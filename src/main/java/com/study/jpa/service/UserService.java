package com.study.jpa.service;

import com.study.jpa.entity.TokenInfo;
import com.study.jpa.entity.User;
import com.study.jpa.jwt.JwtTokenProvider;
import com.study.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService{

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final JwtTokenProvider jwtTokenProvider;


    @Transactional
    public TokenInfo login(String userEmail, String password){

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEmail, password);
        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        try {
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
            log.info("authentication getAuthorities: {}", authentication.getAuthorities());
            return tokenInfo;
        }catch (RuntimeException e){
            log.info("음");
        }
        log.info("여기까지도 안옴... 대체 뭐가 문제지..");
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        return TokenInfo.builder().grantType("").accessToken("").refreshToken("").build();
    }


    public void saveUser(User user){
        if(!userRepository.existsById(user.getUsername())){
            userRepository.save(user);
            return;
        }
        throw new RuntimeException("이미 등록된 이메일 입니다.");
    }

}
