package com.example.test_sql.repository;

import com.example.test_sql.dto.SinhVienDTO;
import com.example.test_sql.model.Sinhvien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SinhVienRepository extends JpaRepository<Sinhvien,String> {
    @Query("select sv from Sinhvien sv where sv.TenSV like %?1% or sv.MaSinhVien like %?1% or sv.MaLop.MaLop like %?1%")
    List<Sinhvien> search(String search);
}
