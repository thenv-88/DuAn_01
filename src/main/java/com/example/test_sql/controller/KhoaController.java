package com.example.test_sql.controller;

import com.example.test_sql.dto.KhoaDTO;
import com.example.test_sql.model.Khoa;
import com.example.test_sql.repository.KhoaRepository;
import com.example.test_sql.service.KhoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/khoa")
public class KhoaController {
    @Autowired
    KhoaService khoaService ;

    @PreAuthorize("hasAuthority('list_khoa')")
    @RequestMapping(value = "/list/{page}",method = RequestMethod.GET)
    public String getlist(Model model, @PathVariable Integer page, @Param("search") String search){
        Pageable pageable = PageRequest.of(page-1,5);
        Integer listpage = (khoaService.List().size())/5 + 1;
        if(search != null){
            model.addAttribute("khoa",khoaService.search(search));
        }
        else {
            model.addAttribute("khoa",khoaService.getlist(pageable));
        }

        model.addAttribute("page",page);
        model.addAttribute("listpage",listpage);
       return "/admin/khoa/index.html";
    };

    @PreAuthorize("hasAuthority('create_khoa')")
    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public @ResponseBody KhoaDTO Post(@RequestBody KhoaDTO khoaDTO){
        khoaService.Post(khoaDTO);
        return khoaDTO;
    };

    @RequestMapping(value = "/get/{makhoa}",method = RequestMethod.GET)
    public @ResponseBody KhoaDTO get(@PathVariable("makhoa") String makhoa) {
        KhoaDTO khoaDTOget = khoaService.get(makhoa);
        return khoaDTOget;
    }


    @PreAuthorize("hasAuthority('update_khoa')")
    @RequestMapping(value = "/put/{makhoa}",method = RequestMethod.PUT)
    public @ResponseBody KhoaDTO Put(@RequestBody KhoaDTO khoaDTO, @PathVariable String makhoa){
        khoaService.Put(khoaDTO,makhoa);
        return khoaDTO;
    };

   // @PreAuthorize("hasAuthority('delele_khoa')")
    @RequestMapping(value = "/delete/{makhoa}",method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable("makhoa") String makhoa){
        khoaService.Delete(makhoa);
        return "oke";
    };

}
