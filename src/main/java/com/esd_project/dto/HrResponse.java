package com.esd_project.dto;

import com.esd_project.entity.HR;

public record HrResponse(
        String firstName,
        String lastName,
        String email,
        String contactNumber) {
    public static HrResponse fromEntity(HR hr) {
        return new HrResponse(
                hr.getFirstName(),
                hr.getLastName(),
                hr.getEmail(),
                hr.getContactNumber()
        );
    }
}
