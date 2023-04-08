package com.study.jpa.repository;

import com.study.jpa.entity.MemberLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLogRepository extends JpaRepository<MemberLog,Long> {
}
