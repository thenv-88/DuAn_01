package com.example.test_sql;

import com.example.test_sql.model.User;
import com.example.test_sql.repository.UserRepository;
import com.example.test_sql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TestSqlApplication.class);


    }

}
