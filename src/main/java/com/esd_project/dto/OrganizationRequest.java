package com.esd_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public record OrganizationRequest(
        @NotBlank(message = "Name of organization should be present")
        @JsonProperty("name")
        @Column(unique = true, nullable = false)
        String name,

        @NotBlank(message = "Address should not be empty")
        @JsonProperty("address")
        String address,

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
        String contact_number)
{}
