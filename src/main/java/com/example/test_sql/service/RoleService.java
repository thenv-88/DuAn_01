package com.example.test_sql.service;

import com.example.test_sql.dto.RoleDTO;
import com.example.test_sql.mapper.RoleMapper;
import com.example.test_sql.model.Authority;
import com.example.test_sql.model.Role;
import com.example.test_sql.model.User;
import com.example.test_sql.repository.AuthorityRepository;
import com.example.test_sql.repository.RoleRepository;
import com.example.test_sql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class RoleService {
    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    public List<RoleDTO> getList(Pageable pageable){
        List<Role> roles = roleRepository.findAll(pageable).getContent();
        List<RoleDTO> roleDTOList = new ArrayList<>();
        for (Role role:roles) {
            RoleDTO roleDTO = roleMapper.toRoleDTO(role);
            roleDTOList.add(roleDTO);
        }
        return roleDTOList;
    }
    public List<RoleDTO> List(){
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> roleDTOList = new ArrayList<>();
        for (Role role:roles) {
            RoleDTO roleDTO = roleMapper.toRoleDTO(role);
            roleDTOList.add(roleDTO);
        }
        return roleDTOList;
    }

    public RoleDTO post(RoleDTO roleDTO){
        Role rolepost = roleMapper.toRole(roleDTO);
        List<String> stringListUser = roleDTO.getUsers();
        List<User> userList = new ArrayList<>();
        for(int i=0;i<stringListUser.size();i++){
            User user = userRepository.findByEmail(stringListUser.get(i));
            userList.add(user);
        }
        rolepost.setUsers(userList);
        List<String> stringListAuthority = roleDTO.getAuthoritys();
        List<Authority> authorities = new ArrayList<>();
        for (int i=0;i<stringListAuthority.size();i++){
            Authority authority = authorityRepository.findByName(stringListAuthority.get(i));
            authorities.add(authority);
        }
        rolepost.setAuthorities(authorities);
        roleRepository.save(rolepost);
        return roleDTO;
    }
    public RoleDTO getone(long id){
        Optional<Role> role =roleRepository.findById(id);
        RoleDTO roleDTO = roleMapper.toRoleDTO(role.get());
        return roleDTO;
    }

    public List<RoleDTO> search(String search){
        List<Role> roleList = roleRepository.search(search);
        List<RoleDTO> roleDTOList= new ArrayList<>();
        for (Role role: roleList) {
            RoleDTO roleDTO = roleMapper.toRoleDTO(role);
            roleDTOList.add(roleDTO);

        }
        return roleDTOList;
    }
    public RoleDTO put(RoleDTO roleDTO,long id){
        Role roleput = roleRepository.findById(id).get();
        roleput.setName(roleDTO.getName());
        List<String> stringList = roleDTO.getAuthoritys();
        List<Authority> authorities = new ArrayList<>();
        for (int i=0;i<stringList.size();i++){
            Authority authority = authorityRepository.findByName(stringList.get(i));
            authorities.add(authority);
        }
        roleput.setAuthorities(authorities);
        List<String> stringList1 = roleDTO.getUsers();
        List<User> users = new ArrayList<>();
        for (int i=0;i<stringList1.size();i++){
            User user = userRepository.findByEmail(stringList1.get(i));
            users.add(user);
        }
        roleput.setUsers(users);
        roleRepository.save(roleput);
        return roleDTO;

    }

    public String delete(long id){
        Role role = roleRepository.findById(id).get();
        if(role.getUsers().size() == 0){
            roleRepository.deleteById(id);
            return "okee";
        }
        else {
            throw new RuntimeException("không hợp lệ ");
        }
    }
}
