package com.study.jpa.repository;


import com.study.jpa.entity.Member;
import com.study.jpa.entity.MemberLog;
import com.study.jpa.tool.TimeFormat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MemberBulkRepositoryTest {

    private static final int COUNT = 10000;

    @Autowired
    private MemberBulkRepository memberBulkRepository;



    @Test
    @DisplayName("회원 10000명 테스트")
    void saveAllTest(){
        String[] randomNameArray = {"박가네","김가네","제임스"};
        List<Member> memberList = new ArrayList<>();
        for(long i = 0; i < COUNT; i++){
            memberList.add(new Member(randomNameArray[(int) (Math.random()* randomNameArray.length)], (long) (Math.random()* 1000)));
        }
        MemberLog memberLog = new MemberLog(100L, LocalDateTime.now().format(TimeFormat.memberBulkRegisterDateFormat));
        memberBulkRepository.saveAll(memberList, memberLog);
    }
}
