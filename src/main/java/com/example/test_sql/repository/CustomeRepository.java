package com.example.test_sql.repository;

import com.example.test_sql.model.Sinhvien;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CustomeRepository extends JpaRepository<Sinhvien,String> {
    @Query("select s.TenSV,b.Diem  from Sinhvien s inner join Lop l on s.MaLop= l.MaLop inner join Baithi b on s.MaSinhVien = b.MaSinhVien where b.Diem > 4")
    List<Object> test();
}
