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
                    .first_name(request.first_name())
                    .last_name(request.last_name())
                    .email(request.email())
                    .contact_number(request.contact_number())
                    .organization(organization)
                    .build();
        }
}
