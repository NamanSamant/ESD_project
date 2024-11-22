package com.esd_project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private int departmentId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int capacity;
}
