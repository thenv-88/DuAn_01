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
@Table(name = "monhoc")

public class Monhoc {
    @Id
    private String MaMonHoc;

    private String TenMonHoc;
    @OneToMany(mappedBy = "MaMonHoc",cascade = CascadeType.ALL)
    private List<Baithi> baithis;

}
