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
public class RoleDTO {
    private long id;
    private String name;
    private List<String> users;
    private List<String> authoritys;
}
