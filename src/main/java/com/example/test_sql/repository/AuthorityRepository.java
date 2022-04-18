package com.example.test_sql.repository;

import com.example.test_sql.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorityRepository extends JpaRepository<Authority,String> {
    @Query("select a  from Authority a where a.authority_name =?1")
    Authority findByName(String name);
}
