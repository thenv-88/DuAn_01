package com.example.test_sql.repository;

import com.example.test_sql.model.Khoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface KhoaRepository extends JpaRepository<Khoa,String> {
    @Query("select k from Khoa k where k.TenKhoa = ?1")
    Khoa khoaByName(String tenkhoa);

    @Query("select k from Khoa k where k.TenKhoa like %?1%")
    List<Khoa> search(String search);
}
