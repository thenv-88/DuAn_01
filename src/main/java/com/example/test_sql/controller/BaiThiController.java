package com.example.test_sql.controller;

import com.example.test_sql.dto.BaiThiDTO;
import com.example.test_sql.model.Baithi;
import com.example.test_sql.model.Sinhvien;
import com.example.test_sql.repository.BaiThiRepository;
import com.example.test_sql.repository.MonHocRepository;
import com.example.test_sql.service.BaiThiService;
import com.example.test_sql.service.MonHocService;
import com.example.test_sql.service.SinhVienService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/admin/baithi")
public class BaiThiController {
    @Autowired
    private BaiThiService baiThiService ;

    @Autowired
    private MonHocService monHocService;

    @Autowired
    private SinhVienService sinhVienService;

    @PreAuthorize("hasAuthority('list_baithi')")
    @GetMapping("/list/{page}")
    public String list(@PathVariable Integer page, Model model, @Param("search") Integer search){

        Integer p = page-1;
        Pageable pageable = PageRequest.of(p,5);
        Integer listpage = (baiThiService.List().size())/5 + 1;
        if(search != null){
            model.addAttribute("baithi",baiThiService.search(search));
        }
        else {
            model.addAttribute("baithi",baiThiService.getlist(pageable));
        }
        model.addAttribute("page",page);
        model.addAttribute("listpage",listpage);
        model.addAttribute("sinhvien",sinhVienService.List());
        model.addAttribute("monhoc",monHocService.List());
        return "admin/baithi/index.html";
    }

    @GetMapping("/get/{mabaithi}")
    public @ResponseBody BaiThiDTO get(@PathVariable Long mabaithi){
        BaiThiDTO baiThiDTO = baiThiService.getone(mabaithi);
        return baiThiDTO;
    }


    @PreAuthorize("hasAuthority('create_baithi')")
    @PostMapping("/post")
    public @ResponseBody BaiThiDTO post(@RequestBody BaiThiDTO baiThiDTO){

        baiThiService.post(baiThiDTO);
        return baiThiDTO;
    }

    @PreAuthorize("hasAuthority('update_baithi')")
    @PutMapping("/put/{mabaithi}")
    public @ResponseBody BaiThiDTO put(@RequestBody BaiThiDTO baiThiDTO,@PathVariable Long mabaithi){
        baiThiService.put(baiThiDTO,mabaithi);
        return baiThiDTO;

    }

    @PreAuthorize("hasAuthority('delete_baithi')")
    @DeleteMapping("/delete/{mabaithi}")
    public @ResponseBody String delete(@PathVariable("mabaithi") Long mabaithi){
        baiThiService.delete(mabaithi);
        return "Xóa thành công";

    }

}
