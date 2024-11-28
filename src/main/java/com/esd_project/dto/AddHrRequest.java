package com.esd_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddHrRequest(
        @NotBlank(message = "first name should be present")
        @JsonProperty("first_name")
        String first_name,

        @JsonProperty("last_name")
        String last_name,

        @NotNull(message="email is required")
        @Email(message = "Email must be in correct format")
        @JsonProperty("email")
        String email,

        @NotNull(message = "Address is required")
        @JsonProperty("contact_number")
        String contact_number) {
    public String getFirstName() {
        return first_name;
    }
    public String getLastName() {
        return last_name;
    }
    public String getContactNumber() {
        return contact_number;
    }
    public String getEmail() {
        return email;
    }
}

