package com.example.test_sql.mapper;

import com.example.test_sql.dto.BaiThiDTO;
import com.example.test_sql.model.Baithi;
import org.springframework.stereotype.Component;

@Component
public class BaiThiMapper {
    public Baithi baithi(BaiThiDTO baiThiDTO){
        Baithi baithi = new Baithi();
        baithi.setMaBaiThi(baiThiDTO.getMaBaiThi());
        baithi.setDiem(baiThiDTO.getDiem());
        return baithi;
    }
    public BaiThiDTO baiThiDTO(Baithi baithi){
        BaiThiDTO baiThiDTO = new BaiThiDTO();
        baiThiDTO.setMaMonHoc(baithi.getMaMonHoc().getMaMonHoc());
        baiThiDTO.setMaBaiThi(baithi.getMaBaiThi());
        baiThiDTO.setMaSinhVien(baithi.getMaSinhVien().getMaSinhVien());
        baiThiDTO.setDiem(baithi.getDiem());
        return baiThiDTO;
    }
}
