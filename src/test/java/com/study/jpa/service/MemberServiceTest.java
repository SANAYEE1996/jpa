package com.study.jpa.service;

import com.study.jpa.entity.Member;
import com.study.jpa.entity.MemberLog;
import com.study.jpa.repository.MemberBulkRepository;
import com.study.jpa.repository.MemberLogRepository;
import com.study.jpa.tool.TimeFormat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MemberServiceTest {

    private static final int COUNT = 10000;

    @Autowired
    private MemberLogRepository memberLogRepository;

    @Autowired
    private MemberBulkRepository memberBulkRepository;

    @Test
    @DisplayName("10000명의 멤버와 그 로그를 저장하는 테스트")
    void memberLogSaveTest(){
        String[] randomNameArray = {"박가네","김가네","제임스"};
        List<Member> memberList = new ArrayList<>();
        Long count = 0L;
        Long value;
        for(long i = 0; i < COUNT; i++){
            value = (long) (Math.random()* 1000);
            memberList.add(new Member(randomNameArray[(int) (Math.random()* randomNameArray.length)], value));
            count += value;
        }
        MemberLog memberLog = new MemberLog(count, LocalDateTime.now().format(TimeFormat.memberBulkRegisterDateFormat));
        memberLogRepository.save(memberLog);
        System.out.println(memberLog.getId());
        assertTrue(memberLog.getId() != null);
        memberBulkRepository.saveAll(memberList, memberLog);
    }
}
