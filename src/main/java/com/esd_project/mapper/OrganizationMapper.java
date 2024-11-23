package com.esd_project.mapper;

import com.esd_project.dto.OrganizationRequest;
import com.esd_project.entity.Organization;
import org.springframework.stereotype.Service;
import com.esd_project.mapper.OrganizationMapper;

@Service
public class OrganizationMapper {
        public Organization toOrganization(OrganizationRequest request) {
            return Organization.builder()
                    .name(request.name())
                    .address(request.address())
                    .build();
        }
}
