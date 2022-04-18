package com.example.test_sql.repository;

import com.example.test_sql.model.Baithi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BaiThiRepository extends JpaRepository<Baithi,Long> {
    @Query("select b from Baithi b where b.Diem=?1")
    List<Baithi> searchdiem(Integer diem);

}
