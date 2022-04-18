package com.example.test_sql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class BaiThiDTO {
    private Long MaBaiThi;

    private String MaMonHoc;

    private String MaSinhVien;

    private Integer Diem;
}
