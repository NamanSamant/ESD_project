package com.esd_project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employee_id;

    @Column(nullable = false)
    private String first_name;

    @Column
    private String last_name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String title;

    @Column
    private String photograph_path;

    @ManyToOne
    @JoinColumn(name = "department", referencedColumnName = "department_id", nullable = false)
    private Department department;
}
