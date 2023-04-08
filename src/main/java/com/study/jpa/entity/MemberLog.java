package com.study.jpa.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "member_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "count")
    private Long count;

    @Column(name = "regdate")
    private String registerDate;

    public MemberLog(Long count, String registerDate) {
        this.count = count;
        this.registerDate = registerDate;
    }
}
