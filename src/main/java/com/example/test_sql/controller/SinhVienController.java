package com.example.test_sql.controller;

import com.example.test_sql.dto.SinhVienDTO;
import com.example.test_sql.service.LopService;
import com.example.test_sql.service.SinhVienService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Controller
@RequestMapping("/admin/sinhvien")
public class SinhVienController {
    @Autowired
    SinhVienService sinhVienService;

    @Autowired
    LopService lopService;

    @PreAuthorize("hasAuthority('list_sinhvien')")
    @GetMapping("/list/{page}")
    public String list(@Param("search") String search, @PathVariable Integer page,Model model){
        Integer p = page-1;
        Integer listpage = (sinhVienService.List().size())/5 + 1;
        Pageable pageable = PageRequest.of(p,5);
        if(search != null){
            model.addAttribute("sv",sinhVienService.search(search));

        }
        else {
            model.addAttribute("sv",sinhVienService.getList(pageable));
        }
        model.addAttribute("lop",lopService.List());
        model.addAttribute("page",page);
        model.addAttribute("listpage",listpage);

        return "/admin/sinhvien/index.html";
    }

    @PreAuthorize("hasAuthority('create_sinhvien')")
    @PostMapping("/post")
    public @ResponseBody String post(@ModelAttribute SinhVienDTO sinhVienDTO){
        sinhVienService.post(sinhVienDTO);
        return "okee";
    }



    @PreAuthorize("hasAuthority('update_sinhvien')")
    @GetMapping("/get/{masinhvien}")
    public @ResponseBody SinhVienDTO getone(@PathVariable String masinhvien ) {
        SinhVienDTO sinhVienDTO = sinhVienService.getone(masinhvien);
        return sinhVienDTO;
    }


    @PutMapping("/put/{masinhvien}")
    public @ResponseBody String put(@ModelAttribute SinhVienDTO sinhVienDTO,@PathVariable String masinhvien){

         sinhVienService.put(sinhVienDTO,masinhvien);
         return "okee";
    }

    @PreAuthorize("hasAuthority('delete_sinhvien')")
    @DeleteMapping ({"/delete/{masinhvien}"})
    public @ResponseBody String delete(@PathVariable String masinhvien){
        sinhVienService.delete(masinhvien);
        return "okee";
    }
}
