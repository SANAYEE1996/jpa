package com.study.jpa.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sno")
    private Long sno;

    @Column(name = "name")
    private String username;

    @Column(name = "count")
    private Long count;


    public Member(String username, Long count) {
        this.username = username;
        this.count = count;
    }
}
