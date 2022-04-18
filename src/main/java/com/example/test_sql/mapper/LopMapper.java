package com.example.test_sql.mapper;

import com.example.test_sql.dto.LopDTO;
import com.example.test_sql.model.Lop;
import org.springframework.stereotype.Component;

@Component
public class LopMapper {
    public Lop toLop(LopDTO dto){
        Lop lop = new Lop();
        lop.setMaLop(dto.getMaLop());
        lop.setTenLop(dto.getTenLop());
        return lop;
    }

    public LopDTO toDTO(Lop lop){
        LopDTO dto = new LopDTO();
        dto.setTenLop(lop.getTenLop());
        dto.setMaLop(lop.getMaLop());
        dto.setTenKhoa(lop.getTenKhoa().getMaKhoa());
        return  dto;
    }

}
