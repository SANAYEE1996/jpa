package com.study.jpa.securityConfig;

import com.study.jpa.annotation.WithUser;
import com.study.jpa.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.stereotype.Component;

@Component
public class WithUserSecurityContextFactory implements WithSecurityContextFactory<WithUser> {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Override
    public SecurityContext createSecurityContext(WithUser annotation) {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(annotation.userEmail());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContext context = SecurityContextHolder.createEmptyContext();

        context.setAuthentication(authenticationToken);

        return context;
    }
}
