package com.example.test_sql.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class UserDTO {
    private long id;
    private String name;
    private String email;
    private String password;
    private List<String> roles;
}
