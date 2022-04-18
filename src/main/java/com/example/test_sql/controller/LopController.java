package com.example.test_sql.controller;

import com.example.test_sql.dto.KhoaDTO;
import com.example.test_sql.dto.LopDTO;
import com.example.test_sql.model.Lop;
import com.example.test_sql.service.KhoaService;
import com.example.test_sql.service.LopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin/lop")
public class LopController {
    @Autowired
    public LopService lopService ;

    @Autowired
    public KhoaService khoaService;

    @PreAuthorize("hasAuthority('list_lop')")
    @GetMapping("/list/{page}")
    public String getList(@PathVariable Integer page, Model model,@Param("search") String search){
        Integer p = page-1;
        Pageable pageable = PageRequest.of(p,5);
        Integer listpage = (lopService.List().size())/5 + 1;
        if(search != null){
            model.addAttribute("lop",lopService.search(search));
        }
        else {
            model.addAttribute("lop",lopService.getList(pageable));
        }

        model.addAttribute("khoa",khoaService.List());
        model.addAttribute("page",page);
        model.addAttribute("listpage",listpage);
        return "admin/lop/index.html";
    }


    @GetMapping("/get/{malop}")
    public @ResponseBody LopDTO get(@PathVariable String malop){
        LopDTO lopDTO = lopService.getone(malop);
        return lopDTO;
    }

    @PreAuthorize("hasAuthority('create_lop')")
    @PostMapping("/post")
    public @ResponseBody LopDTO post(@RequestBody LopDTO lopDTO){
        lopService.post(lopDTO);
        return lopDTO;
    }

    @PreAuthorize("hasAuthority('update_lop')")
    @PutMapping("/put/{malop}")
    public @ResponseBody LopDTO put(@RequestBody LopDTO lopDTO ,@PathVariable String malop){
        lopService.put(lopDTO,malop);
        return lopDTO;
    }

    @PreAuthorize("hasAuthority('delete_lop')")
    @DeleteMapping("delete/{malop}")
    public @ResponseBody String delete(@PathVariable("malop") String malop){
        lopService.delete(malop);
        return "Xóa thành công";
    }
}
