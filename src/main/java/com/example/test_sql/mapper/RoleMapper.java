package com.example.test_sql.mapper;

import com.example.test_sql.dto.RoleDTO;
import com.example.test_sql.model.Authority;
import com.example.test_sql.model.Role;
import com.example.test_sql.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleMapper {
    public Role toRole(RoleDTO roleDTO){
        Role role = new Role();
        role.setName(roleDTO.getName());
        return role;

    }
    public RoleDTO toRoleDTO(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        List<String> user = new ArrayList<>();
        List<User> list = role.getUsers();
        for (int i=0;i<list.size();i++){
            user.add(list.get(i).getEmail());
        }
        roleDTO.setUsers(user);
        List<String> au = new ArrayList<>();
        List<Authority> authorityList = role.getAuthorities();
        for (int i=0 ;i<authorityList.size();i++){
            au.add(authorityList.get(i).getAuthority_name());
        }
        roleDTO.setAuthoritys(au);
        return roleDTO;
    }

}
