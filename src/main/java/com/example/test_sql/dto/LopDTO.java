package com.example.test_sql.dto;

import com.example.test_sql.model.Khoa;
import com.example.test_sql.model.Sinhvien;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class LopDTO {
    private String MaLop;

    private String TenLop;

    private String TenKhoa;

}
