package com.example.test_sql.repository;

import com.example.test_sql.model.Monhoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonHocRepository extends JpaRepository<Monhoc,String> {
    @Query("select m from Monhoc m where m.MaMonHoc like %?1% or m.TenMonHoc like %?1%")
    List<Monhoc> search(String search);
}
