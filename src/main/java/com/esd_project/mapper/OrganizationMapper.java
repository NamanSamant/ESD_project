package com.esd_project.mapper;

import com.esd_project.dto.OrganizationRequest;
import com.esd_project.entity.HR;
import com.esd_project.entity.Organization;
import org.springframework.stereotype.Service;

@Service
public class OrganizationMapper {
        public Organization toOrganization(OrganizationRequest request) {
            return Organization.builder()
                    .name(request.name())
                    .address(request.address())
                    .build();
        }
        public HR toHR(Organization organization, OrganizationRequest request){
            return HR.builder()
                    .firstName(request.firstName())
                    .lastName(request.lastName())
                    .email(request.email())
                    .contactNumber(request.contactNumber())
                    .organization(organization)
                    .build();
        }
}
