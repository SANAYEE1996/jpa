package com.study.jpa.repository;

import com.study.jpa.entity.Member;
import com.study.jpa.entity.MemberLog;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberBulkRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveAll(List<Member> memberList, MemberLog memberLog){
        String sql = "INSERT INTO MEMBER (sno, name, count) "+
                     "VALUES (?, ?, ?) ";

        jdbcTemplate.batchUpdate(sql,
                                 memberList,
                                 memberList.size(),
                                 (PreparedStatement ps, Member member) -> {
                                    ps.setLong(1, memberLog.getId());
                                     ps.setString(2, member.getUsername());
                                     ps.setLong(3, member.getCount());
                                 });

    }
}
