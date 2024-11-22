package com.esd_project.service;

import com.esd_project.dto.LoginRequest;
import com.esd_project.helper.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.esd_project.repo.EmployeeRepo;
import com.esd_project.repo.CredentialsRepo;
import com.esd_project.entity.Employee;
import com.esd_project.entity.Credentials;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final CredentialsRepo credentialsRepo;
    private final EncryptionService encryptionService;

    public boolean login(LoginRequest request) {
        Employee employee = employeeRepo.findByEmail(request.email());

        // Fetch credentials by email
        Credentials credentials = credentialsRepo.findByEmail(request.email());

        // Return true only if both employee and credentials exist, password matches, and department is "Outreach"
        if(!encryptionService.validates(request.password(), credentials.getPassword())) return false;
        return employee != null && employee.getDepartment() != null && employee.getDepartment().getDepartmentId() == 1;
    }
}
