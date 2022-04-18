package com.example.test_sql.mapper;

import com.example.test_sql.dto.KhoaDTO;
import com.example.test_sql.model.Khoa;
import org.springframework.stereotype.Component;

@Component
public class KhoaMapper {
    public KhoaDTO toDTO(Khoa khoa){
        KhoaDTO khoaDTO = new KhoaDTO();
        khoaDTO.setMaKhoa(khoa.getMaKhoa());
        khoaDTO.setTenKhoa(khoa.getTenKhoa());
        khoaDTO.setDiaChi(khoa.getDiaChi());
        return khoaDTO;
    }

    public Khoa toKhoa(KhoaDTO khoaDTO){
        Khoa khoa = new Khoa();
        khoa.setMaKhoa(khoaDTO.getMaKhoa());
        khoa.setTenKhoa(khoaDTO.getTenKhoa());
        khoa.setDiaChi(khoaDTO.getDiaChi());
        return khoa;
    }
}
