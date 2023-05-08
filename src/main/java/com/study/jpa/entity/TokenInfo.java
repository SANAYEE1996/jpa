package com.study.jpa.entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class TokenInfo {
    private String grantType;

    private String accessToken;

    private String refreshToken;

    private String message;
}
