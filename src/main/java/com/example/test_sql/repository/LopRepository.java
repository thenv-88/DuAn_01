package com.example.test_sql.repository;

import com.example.test_sql.model.Lop;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface LopRepository extends JpaRepository<Lop,String> {
    @Query("select l from Lop l where l.TenLop like %?1%")
    List<Lop> search(String search);
}
