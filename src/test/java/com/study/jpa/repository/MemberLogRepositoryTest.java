package com.study.jpa.repository;

import com.study.jpa.entity.MemberLog;
import com.study.jpa.tool.TimeFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class MemberLogRepositoryTest {


    @Autowired
    private MemberLogRepository memberLogRepository;

    @Test
    void remainCacheTest(){
        MemberLog memberLog = new MemberLog(100L, LocalDateTime.now().format(TimeFormat.memberBulkRegisterDateFormat));
        memberLogRepository.save(memberLog);
        System.out.println(memberLog.getId());
        assertTrue(memberLog.getId() != null);
    }
}
