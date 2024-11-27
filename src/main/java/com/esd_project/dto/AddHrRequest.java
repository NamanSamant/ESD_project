package com.esd_project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
public class AddHrRequest {
    // Getters and Setters
    @Getter
    @NotBlank
    private String first_name;

    private String last_name;

    @Getter
    @Email
    @NotBlank
    private String email;

    private String contact_number;

    public String getLastName() {
        return last_name;
    }

    public String getContactNumber() {
        return contact_number;
    }

}

