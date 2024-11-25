
package com.esd_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public record UpdateOrganizationRequest(
        @JsonProperty("name")
        @Column(unique = true)
        String name,

        @JsonProperty("address")
        String address,

        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        @Pattern(
                regexp = "^(|[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$",
                message = "Email must be in correct format"
        )
        @JsonProperty("email")
        String email,

        @JsonProperty("contact_number")
        String contactNumber)
{}
