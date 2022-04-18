package com.example.test_sql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "lop")

public class Lop {
    @Id
    private String MaLop;


    @Column(name = "TenLop")
    private String TenLop;

    @ManyToOne
    @JoinColumn(name = "TenKhoa",nullable = false)
    private Khoa TenKhoa;

    @OneToMany(mappedBy = "MaLop", cascade = CascadeType.ALL)
    private List<Sinhvien>  sinhviens;
}
