package com.example.test_sql.repository;

import com.example.test_sql.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query( "select u from User u where u.email = ?1")
    User findByEmail(String email);

    @Query("select u from User u where  u.name like %?1%")
    List<User> search(@Param("search") String search);

}
