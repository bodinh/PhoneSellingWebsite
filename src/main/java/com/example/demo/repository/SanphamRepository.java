package com.example.demo.repository;

import com.example.demo.Hibernate.SanphamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Tran Tuan Anh
 * @Created: Sun, 14/03/2021 5:31 PM
 **/

@Repository
public interface SanphamRepository extends JpaRepository<SanphamEntity, Long> {
    @Query(value = "select * from Sanpham sp where sp.TenSP like '%sam%'")
    List<SanphamEntity> findProductByKeyword(@Param("keyword") String keyword);


    public static void main(String[] args) {

    }
}
