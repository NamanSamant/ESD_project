package com.esd_project.service;

import com.esd_project.dto.HrResponse;
import com.esd_project.dto.OrganizationRequest;
import com.esd_project.dto.SearchResponse;
import com.esd_project.dto.UpdateOrganizationRequest;
import com.esd_project.entity.HR;
import com.esd_project.entity.Organization;
import com.esd_project.mapper.OrganizationMapper;
import com.esd_project.repo.HrRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.esd_project.repo.OrganizationRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationMapper organizationMapper;
    private final OrganizationRepo organizationRepo;
    private final HrRepo hrRepo;

    // FUNCTION FOR CREATING AN ORGANIZATION
    public String createCustomer(OrganizationRequest request) {
        Optional<Organization> existingOrganizationOpt = organizationRepo.findByName(request.name());
        Organization organization;
        if (existingOrganizationOpt.isPresent()) {
            organization = existingOrganizationOpt.get();
            HR hr = organizationMapper.toHR(organization, request);
            hr.setOrganization(organization);
            hrRepo.save(hr);
            return "Organization already exists. HR entry created.";
        }
        else {
        organization = organizationMapper.toOrganization(request);
        Organization savedOrganization = organizationRepo.save(organization);
        HR hr = organizationMapper.toHR(savedOrganization, request);
        hrRepo.save(hr);
        return "Organization Created Successfully";
    }}

    public List<Organization> getAllOrganizations() {
        return organizationRepo.findAllWithHR();
    }

    public List<HrResponse> getHRsByOrganization(Long organizationId) {
        return hrRepo.findByOrganizationId(organizationId)
                .stream()
                .map(HrResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public SearchResponse getHRsByOrganizationName(String organizationName) {
        // Fetch organization by name
        Organization organization = organizationRepo.findByName(organizationName)
                .orElseThrow(() -> new RuntimeException("Organization not found with name: " + organizationName));

        // Fetch HRs by organization ID
        List<HR> hrList = hrRepo.findByOrganizationId(organization.getId());

        // Map to response
        return SearchResponse.fromEntities(organization, hrList);
    }

    public void deleteHRById(Long hrId) {
        // Check if HR exists
        HR hr = hrRepo.findById(hrId)
                .orElseThrow(() -> new RuntimeException("HR not found with ID: " + hrId));

        // Delete HR
        hrRepo.delete(hr);

        // Check if the associated organization still has HRs
        Long organizationId = hr.getOrganization().getId();
        if (!hrRepo.existsByOrganizationId(organizationId)) {
            // If no HRs remain, delete the organization
            organizationRepo.deleteById(organizationId);
        }
    }

    public void deleteByOrganizationId(Long organizationId) {
        // Check if organization exists
        Organization organization = organizationRepo.findById(organizationId)
                .orElseThrow(() -> new RuntimeException("Organization not found with ID: " + organizationId));

        // Delete associated HRs
        hrRepo.deleteByOrganizationId(organizationId);

        // Delete organization
        organizationRepo.delete(organization);
    }

    // Function to update organization details
    public String updateOrganization(Long organizationId, UpdateOrganizationRequest request) {
        Organization organization = organizationRepo.findById(organizationId)
                .orElseThrow(() -> new RuntimeException("Organization not found with ID: " + organizationId));

        if(request.name()!=null) {
            organization.setName(request.name());
        }
        if(request.address()!=null) {
            organization.setAddress(request.address());
        }

        organizationRepo.save(organization);

        return "Organization updated successfully";
    }

    public String updateHR(Long hrId, UpdateOrganizationRequest request) {
        HR hr = hrRepo.findById(hrId)
                .orElseThrow(() -> new RuntimeException("HR not found with ID: " + hrId));

        if (request.firstName() != null) {
            hr.setFirstName(request.firstName());
        }
        if (request.lastName() != null) {
            hr.setLastName(request.lastName());
        }
        if (request.email() != null) {
            hr.setEmail(request.email());
        }
        if (request.contactNumber() != null) {
            hr.setContactNumber(request.contactNumber());
        }
        hrRepo.save(hr);

        return "HR details updated successfully";
    }
    public String updateOrganizationAndHR(Long organizationId, Long hrId, UpdateOrganizationRequest request) {
        updateOrganization(organizationId, request);
        updateHR(hrId, request);

        return "Organization and HR details updated successfully";
    }
}
