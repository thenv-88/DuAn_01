package com.example.test_sql.mapper;

import com.example.test_sql.dto.MonHocDTO;
import com.example.test_sql.model.Monhoc;
import org.springframework.stereotype.Component;

@Component
public class MonHocMapper {
    public Monhoc monhoc(MonHocDTO monHocDTO){
        Monhoc monhoc = new Monhoc();
        monhoc.setTenMonHoc(monHocDTO.getTenMonHoc());
        monhoc.setMaMonHoc(monHocDTO.getMaMonHoc());
        return monhoc;
    }

    public MonHocDTO monHocDTO(Monhoc monhoc){
        MonHocDTO monHocDTO = new MonHocDTO();
        monHocDTO.setMaMonHoc(monhoc.getMaMonHoc());
        monHocDTO.setTenMonHoc(monhoc.getTenMonHoc());
        return monHocDTO;
    }
}
