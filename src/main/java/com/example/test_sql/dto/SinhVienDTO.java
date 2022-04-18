package com.example.test_sql.dto;

import com.example.test_sql.model.Lop;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class SinhVienDTO {

    private String MaSinhVien;
    private java.sql.Date Date;
    private String TenSV;
    private String DiaChi;
    private String MaLop;
    private String image;
    private MultipartFile multipartFile;
}
