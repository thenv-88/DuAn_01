package com.example.test_sql.controller;

import com.example.test_sql.dto.MonHocDTO;
import com.example.test_sql.service.MonHocService;
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
@RequestMapping("/admin/monhoc")
public class MonHocControlller {
    @Autowired
    public MonHocService monHocService = new MonHocService();

    @PreAuthorize("hasAuthority('list_monhoc')")
    @GetMapping("/list/{page}")
    public String getList(@PathVariable Integer page, Model model, @Param("search") String search){
        Integer p = page-1;
        Pageable pageable = PageRequest.of(p,5);
        Integer listpage = (monHocService.List().size())/5 + 1;
        if(search != null){
            model.addAttribute("monhoc",monHocService.search(search));
        }
        else {
            model.addAttribute("monhoc",monHocService.getList(pageable));
        }
        model.addAttribute("page",page);
        model.addAttribute("listpage",listpage);
        return "admin/monhoc/index.html";
    }

    @GetMapping("/get/{mamonhoc}")
    public @ResponseBody MonHocDTO get(@PathVariable("mamonhoc") String ma){
        MonHocDTO monhoc = monHocService.getone(ma);
        return monhoc;
    }

    @PreAuthorize("hasAuthority('create_monhoc')")
    @PostMapping("/post")
    public @ResponseBody  MonHocDTO Post(@RequestBody MonHocDTO monhoc){
        monHocService.post(monhoc);
        return monhoc;
    }

    @PreAuthorize("hasAuthority('update_monhoc')")
    @PutMapping("/put/{mamonhoc}")
    public @ResponseBody  MonHocDTO put(@RequestBody MonHocDTO monhoc,@PathVariable String mamonhoc){
        monHocService.put(monhoc,mamonhoc);
        return monHocService.put(monhoc,mamonhoc);
    }

    @PreAuthorize("hasAuthority('delete_monhoc')")
    @DeleteMapping("/delete/{mamonhoc}")
    public @ResponseBody String   delete(@PathVariable("mamonhoc") String ma){
        monHocService.delete(ma);
        return "okee";
    }
}
