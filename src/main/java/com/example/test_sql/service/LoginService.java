package com.example.test_sql.service;

import com.example.test_sql.model.Authority;
import com.example.test_sql.model.Role;
import com.example.test_sql.model.User;
import com.example.test_sql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@Component
public class LoginService implements UserDetailsService {
    @Autowired
    public UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            List<Role> roles = user.getRoles();
            List<Authority> authorities = new ArrayList<>();
            for (int i =0 ;i<roles.size();i++){
                for (int j=0;j<roles.get(i).getAuthorities().size();j++){
                    authorities.add(roles.get(i).getAuthorities().get(j));
                }
            }
            List<String> stringList = new ArrayList<>();
            for (Authority authority:authorities) {
                stringList.add(authority.getAuthority_name());
            }
            for (String s:stringList) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(s);
                grantedAuthorities.add(authority);

            }
           // for (Role a : roles) {
               // SimpleGrantedAuthority authority = new SimpleGrantedAuthority(a.getName());
              //  grantedAuthorities.add(authority);
            //}

            UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
            return userDetails;
        } else {
            throw new UsernameNotFoundException("Không tồn tại");
        }
    }
}
