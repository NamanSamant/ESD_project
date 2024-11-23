package com.esd_project.service;

import com.esd_project.dto.OrganizationRequest;
import com.esd_project.entity.Organization;
import com.esd_project.mapper.OrganizationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.esd_project.repo.OrganizationRepo;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationMapper organizationMapper;
    private final OrganizationRepo organizationRepo;

    // FUNCTION FOR CREATING AN ORGANIZATION
    public String createCustomer(OrganizationRequest request) {
        Organization organization = organizationMapper.toOrganization(request);
        organizationRepo.save(organization);
        return "Organization Created Successfully";
    }
}
