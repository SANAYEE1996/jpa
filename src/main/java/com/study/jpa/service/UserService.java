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

        try {
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
            log.info("authentication getAuthorities: {}", authentication.getAuthorities());
            return tokenInfo;
        }catch (RuntimeException e){
            e.printStackTrace();
            log.info("음");
        }

        return TokenInfo.builder().grantType("").accessToken("").refreshToken("").build();
    }


    public void saveUser(User user){
        log.info("여기 들어오기는 합니까?");
        log.info(user.getUserEmail());
        log.info(user.getUserPassword());
        userRepository.save(user);
        log.info("save 하는지 체크");
    }

}
