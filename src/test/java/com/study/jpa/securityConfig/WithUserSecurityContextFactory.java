package com.study.jpa.securityConfig;

import com.study.jpa.annotation.WithUser;
import com.study.jpa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.stereotype.Component;

@Component
public class WithUserSecurityContextFactory implements WithSecurityContextFactory<WithUser> {

    private static final Logger log = LoggerFactory.getLogger(WithUserSecurityContextFactory.class);

    @Autowired
    private UserService userService;

    @Override
    public SecurityContext createSecurityContext(WithUser annotation) {

        UserDetails userDetails = userService.loadUserByUsername(annotation.value());

        log.info("로그인 정보 : {}", userDetails.getUsername());
        log.info("로그인 정보 : {}", userDetails.getPassword());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(), userDetails.getAuthorities());

        log.info("로그인 정보 : {}", authenticationToken.getName());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authenticationToken);
        return context;
    }
}
