package com.example.test_sql.dto;

import com.example.test_sql.model.Lop;
import lombok.*;
import org.springframework.stereotype.Component;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class KhoaDTO {
    private String MaKhoa;

    private String DiaChi;

    private String TenKhoa;

}
