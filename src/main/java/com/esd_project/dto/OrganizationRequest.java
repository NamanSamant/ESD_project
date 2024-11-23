package com.esd_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record OrganizationRequest(
        @NotBlank(message = "Customer should be present")
        @JsonProperty("name")
        String name,

        @NotBlank(message = "Address should not be empty")
        @JsonProperty("address")
        String address)
{}
