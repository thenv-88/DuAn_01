package com.example.test_sql.mapper;

import com.example.test_sql.dto.UserDTO;
import com.example.test_sql.model.Role;
import com.example.test_sql.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public User toUser(UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        String password = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(password);
        return user;
    }
    public UserDTO toUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setId(user.getId());
        List<String> role = new ArrayList<>();
        List<Role> list = user.getRoles();
        for (int i=0;i<list.size();i++){
            role.add(list.get(i).getName());
        }
        userDTO.setRoles(role);
        return userDTO;
    }
}
