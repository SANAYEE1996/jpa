package com.study.jpa.repository;

import com.study.jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from product p where p.id = :id")
    Optional<Product> findWithIdForUpdate(@Param("id") Long id);
}
