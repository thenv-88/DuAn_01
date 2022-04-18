package com.example.test_sql.controller;

import com.example.test_sql.dto.RoleDTO;
import com.example.test_sql.dto.UserDTO;
import com.example.test_sql.model.Authority;
import com.example.test_sql.repository.AuthorityRepository;
import com.example.test_sql.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @Autowired
    AuthorityRepository authorityRepository;

    @PreAuthorize("hasAuthority('list_role')")
    @GetMapping("/list/{page}")
    public  String list(Model model, @PathVariable Integer page, @Param("search") String search){
        Integer p = page-1;
        Pageable pageable = PageRequest.of(p,5);
        Integer listpage = (roleService.List().size())/5 + 1;
        if(search != null){
            model.addAttribute("role",roleService.search(search));
        }
        else {

            List<RoleDTO> roleDTOList = roleService.getList(pageable);
            model.addAttribute("role",roleDTOList);
        }
        model.addAttribute("page",page);
        model.addAttribute("listpage",listpage);
        model.addAttribute("au",authorityRepository.findAll());
        return "admin/role/index.html";
    }

    @PreAuthorize("hasAuthority('create_role')")
    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public @ResponseBody RoleDTO postU(@RequestBody RoleDTO roleDTO){
        roleService.post(roleDTO);
        return roleDTO;
    }

    @GetMapping("/get/{id}")
    public @ResponseBody RoleDTO getone(@PathVariable long id){
        return  roleService.getone(id);
    }

    @PreAuthorize("hasAuthority('create_update')")
    @PutMapping("/put/{id}")
    public @ResponseBody RoleDTO putUser(@RequestBody RoleDTO roleDTO,@PathVariable long id){
        roleService.put(roleDTO,id);
        return roleDTO;
    }

    @PreAuthorize("hasAuthority('delete_role')")
    @DeleteMapping("/delete/{id}")
    public @ResponseBody String delete(@PathVariable(value = "id") long id){
        roleService.delete(id);
        return "oke";
    }

}
