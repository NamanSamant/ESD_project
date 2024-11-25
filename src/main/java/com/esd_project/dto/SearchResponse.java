package com.esd_project.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.esd_project.entity.Organization;
import com.esd_project.entity.HR;

public record SearchResponse(
        String organizationName,
        String organizationAddress,
        List<HrResponse> hrDetails) {
    public static SearchResponse fromEntities(Organization organization, List<HR> hrList) {
        List<HrResponse> hrResponses = hrList.stream()
                .map(HrResponse::fromEntity)
                .collect(Collectors.toList());

        return new SearchResponse(
                organization.getName(),
                organization.getAddress(),
                hrResponses
        );
    }
}
