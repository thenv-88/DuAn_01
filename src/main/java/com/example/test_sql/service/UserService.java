package com.example.test_sql.service;
import com.example.test_sql.dto.UserDTO;
import com.example.test_sql.mapper.UserMapper;
import com.example.test_sql.model.Role;
import com.example.test_sql.model.User;
import com.example.test_sql.repository.RoleRepository;
import com.example.test_sql.repository.UserRepository;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
@Service
public class UserService  {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public RoleRepository roleRepository;

    public List<UserDTO> getList(Pageable pageable){
        List<User> users = userRepository.findAll(pageable).toList();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user:users) {
            UserDTO userDTO = userMapper.toUserDTO(user);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }
    public List<UserDTO> List(){
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user:users) {
            UserDTO userDTO = userMapper.toUserDTO(user);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }
    public UserDTO post(UserDTO userDTO){

            List<String> stringList = userDTO.getRoles();
            User userpost = userMapper.toUser(userDTO);
            List<Role> roleList = new ArrayList<>();
            for (int i=0;i<stringList.size();i++){
                Role role = roleRepository.findByName(stringList.get(i));
                roleList.add(role);
            }
            userpost.setRoles(roleList);
            userRepository.save(userpost);
            return userDTO;
    }
    public UserDTO put(UserDTO userDTO,long id){
        User userput = userRepository.findById(id).get();
        List<String> stringList = userDTO.getRoles();
        userput.setName(userDTO.getName());
        userput.setEmail(userDTO.getEmail());
        userput.setPassword(userDTO.getPassword());

        List<Role> roleList = new ArrayList<>();
        for (int i=0;i<stringList.size();i++){
            Role role = roleRepository.findByName(stringList.get(i));
            roleList.add(role);
        }
        userput.setRoles(roleList);
        userRepository.save(userput);
        return userDTO;
    }


    public UserDTO getone(long id){
        Optional<User> user = userRepository.findById(id);
        UserDTO userDTO = userMapper.toUserDTO(user.get());
        return userDTO;

    }

    public List<UserDTO> search(String search){
        List<User> users =  userRepository.search(search);
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user:users) {
            UserDTO userDTO = userMapper.toUserDTO(user);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    public String delete(long id){
        Optional<User> user = userRepository.findById(id);
        userRepository.deleteById(id);
        return "okee";

    }

}
