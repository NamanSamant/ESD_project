package com.esd_project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}
