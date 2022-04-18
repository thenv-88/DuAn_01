package com.example.test_sql;

import com.example.test_sql.model.Baithi;
import com.example.test_sql.model.Khoa;
import com.example.test_sql.model.Monhoc;
import com.example.test_sql.model.User;
import com.example.test_sql.repository.MonHocRepository;
import com.example.test_sql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication

@RequiredArgsConstructor
public class TestSqlApplication {

    public static void main(String[] args) {

        SpringApplication.run(TestSqlApplication.class, args);
    }

}
