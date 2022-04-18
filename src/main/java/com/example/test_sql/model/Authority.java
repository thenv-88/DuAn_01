package com.example.test_sql.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Setter
@Getter
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "authority_name", nullable = false)
    private String authority_name;

    @ManyToMany()
    @JoinTable(
            name = "authority_role",
            joinColumns = @JoinColumn(name = "authority_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
}
