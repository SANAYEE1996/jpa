package com.study.jpa.repository;

import com.study.jpa.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberBulkRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveAll(List<Member> memberList){
        String sql = "INSERT INTO MEMBER (name) "+
                     "VALUES (?) ";

        jdbcTemplate.batchUpdate(sql,
                                 memberList,
                                 memberList.size(),
                                 (PreparedStatement ps, Member member) -> {
                                    ps.setString(1, member.getUsername());
                                 });

    }
}
