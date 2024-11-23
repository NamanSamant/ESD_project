package com.esd_project.controller;

import com.esd_project.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.esd_project.dto.LoginRequest;
import com.esd_project.helper.JWTHelper;


@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final JWTHelper jwtHelper;

    // POST request for employee login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        boolean loginSuccess = employeeService.login(request);
        if (loginSuccess) {
            String token = jwtHelper.generateToken(request.email());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid email, password, or department");
        }
    }
}
