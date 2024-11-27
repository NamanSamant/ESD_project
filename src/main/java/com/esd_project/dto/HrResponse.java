package com.esd_project.dto;

import com.esd_project.entity.HR;

public record HrResponse(
        long id,
        String first_name,
        String last_name,
        String email,
        String contact_number) {
    public static HrResponse fromEntity(HR hr) {
        return new HrResponse(
                hr.getId(),
                hr.getFirst_name(),
                hr.getLast_name(),
                hr.getEmail(),
                hr.getContact_number()
        );
    }
}
