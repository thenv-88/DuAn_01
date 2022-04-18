package com.example.test_sql.controller;

import com.example.test_sql.model.User;
import com.example.test_sql.repository.UserRepository;
import com.example.test_sql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class loginController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/login")
    public String login(){
        return "admin/login.html";
    }

    @RequestMapping("/user/{name}")
    public @ResponseBody  String a(@PathVariable(value = "name") String name){
        return userRepository.findByEmail(name).getEmail();
    }
    @RequestMapping("/home")
    public String admin(){
        return "home.html";
    }

    @RequestMapping("/a")
    public String admssssin(){
        return "a";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "home.html" ;
    }
    @GetMapping("/index/k")
    public String master(){
        return "admin/user/index.html";
    }
    @GetMapping("/ad")
    public String mastejjjr(){
        return "/admin/layouts/menu";
    }

}
