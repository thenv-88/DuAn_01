package com.example.test_sql.mapper;

import com.example.test_sql.dto.SinhVienDTO;
import com.example.test_sql.model.Sinhvien;
import org.springframework.stereotype.Component;

@Component
public class SinhVienMapper {
    public Sinhvien toSV(SinhVienDTO sinhVienDTO){
        Sinhvien sinhvien = new Sinhvien();
        sinhvien.setMaSinhVien(sinhVienDTO.getMaSinhVien());
        sinhvien.setDate(sinhVienDTO.getDate());
        sinhvien.setTenSV(sinhVienDTO.getTenSV());
        sinhvien.setDiaChi(sinhVienDTO.getDiaChi());
        return sinhvien;

    }

    public SinhVienDTO toSVDTO(Sinhvien sinhvien){
        SinhVienDTO sinhVienDTO = new SinhVienDTO();
        sinhVienDTO.setMaSinhVien(sinhvien.getMaSinhVien());
        sinhVienDTO.setDate(sinhvien.getDate());
        sinhVienDTO.setDiaChi(sinhvien.getDiaChi());
        sinhVienDTO.setTenSV(sinhvien.getTenSV());
        sinhVienDTO.setMaLop(sinhvien.getMaLop().getMaLop());
        sinhVienDTO.setImage(sinhvien.getImage());
        return sinhVienDTO;
    }
}
