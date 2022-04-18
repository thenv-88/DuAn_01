package com.example.test_sql.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "khoa")
public class Khoa {
    @Id
    @Column(name = "MaKhoa",nullable = false)
    private String MaKhoa;

    @Column(name = "DiaChi",nullable = false)
    private String DiaChi;

    @Column(name = "TenKhoa",nullable = false,unique = true)
    private String TenKhoa;

    @OneToMany(mappedBy = "TenKhoa", cascade = CascadeType.ALL )
    private List<Lop> lops;

}
