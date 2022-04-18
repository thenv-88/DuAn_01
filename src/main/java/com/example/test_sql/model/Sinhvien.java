package com.example.test_sql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sinhvien")
public class Sinhvien {
    @Id
    private String MaSinhVien;

    @Column
    private java.sql.Date Date;

    @Column
    private String TenSV;

    @Column
    private String DiaChi;

    @Column
    private String image;

    @ManyToOne
    @JoinColumn(name = "MaLop",nullable = false)
    private Lop MaLop;

    @OneToMany(mappedBy = "MaSinhVien", cascade = CascadeType.ALL)
    private List<Baithi> baithis  ;


}
