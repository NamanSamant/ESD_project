package com.esd_project.controller;

import com.esd_project.dto.OrganizationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.esd_project.service.OrganizationService;

@RestController
@RequestMapping("api/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<String> createOrganization(@RequestBody @Valid OrganizationRequest request) {
        return ResponseEntity.ok(organizationService.createCustomer(request));
    }
}
