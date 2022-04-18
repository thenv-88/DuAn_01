package com.example.test_sql.controller;

import com.example.test_sql.dto.RoleDTO;
import com.example.test_sql.dto.UserDTO;
import com.example.test_sql.service.RoleService;
import com.example.test_sql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    //@PreAuthorize("hasAuthority('list_user')")
    @GetMapping("/admin/user/list/{page}")
    public String list(Model model, @PathVariable Integer page ,@Param("search") String search){
        Integer p = page-1;
        Pageable pageable = PageRequest.of(p,5);
        Integer listpage = (userService.List().size())/5 + 1;
        List<RoleDTO> role = roleService.List();
        if(search !=null){
            model.addAttribute("user",userService.search(search));
        }
        else {
            model.addAttribute("user", userService.getList(pageable));
        }

        model.addAttribute("page",page);
        model.addAttribute("listpage",listpage);
        model.addAttribute("roles",role);
        model.addAttribute("userdto",new UserDTO());
        return "admin/user/index.html";
    }

    @PreAuthorize("hasAuthority('create_update')")
    @GetMapping("/admin/user/get/{id}")
    public @ResponseBody UserDTO getone(@PathVariable long id){
        return  userService.getone(id);
    }

    @PreAuthorize("hasAuthority('create_user')")
    @RequestMapping(value = "/admin/user/post",method = RequestMethod.POST)
    public ResponseEntity<UserDTO> postUser( @RequestBody UserDTO userDTO){
        userService.post(userDTO);
        return ResponseEntity.ok(userDTO);
    }


    @PutMapping("/admin/user/put/{id}")
    public @ResponseBody UserDTO putUser(@RequestBody UserDTO userDTO,@PathVariable long id){
        userService.put(userDTO,id);
        return userDTO;
    }

    @PreAuthorize("hasAuthority('delete_user')")
    @DeleteMapping ("/admin/user/delete/{id}")
    public @ResponseBody String delete(@PathVariable long id){
        return userService.delete(id);
    }

}
