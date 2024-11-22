package com.esd_project.repo;

import com.esd_project.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import com.esd_project.entity.Employee;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    Employee findByEmail(String email);
}
