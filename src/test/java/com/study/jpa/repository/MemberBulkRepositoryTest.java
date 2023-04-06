package com.study.jpa.repository;


import com.study.jpa.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@Rollback
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
            memberList.add(new Member(randomNameArray[(int) Math.random()* randomNameArray.length]));
        }
        memberBulkRepository.saveAll(memberList);
    }
}
