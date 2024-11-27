package com.esd_project.controller;

import com.esd_project.dto.*;
import com.esd_project.entity.Organization;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.esd_project.service.OrganizationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<String> createOrganization(@RequestBody @Valid OrganizationRequest request) {
        return ResponseEntity.ok(organizationService.createOrganization(request));
    }

    @GetMapping
    public ResponseEntity<List<Organization>> getAllOrganizations() {
        return ResponseEntity.ok(organizationService.getAllOrganizations());
    }
    
    @GetMapping("/search_organization")
    public ResponseEntity<Optional<Organization>> getOrganizationByName(@RequestParam String name) {
        return ResponseEntity.ok(organizationService.getOrganizationsByName(name));
    }

    @GetMapping("/{organizationId}/hrs")
    public ResponseEntity<List<HrResponse>> getHRsByOrganization(@PathVariable int organizationId) {
        List<HrResponse> hrList = organizationService.getHRsByOrganization(organizationId);
        if (hrList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(hrList);
    }

    @GetMapping("/search-hrs")
    public ResponseEntity<SearchResponse> getHRsByOrganizationName(@RequestParam String organizationName) {
        SearchResponse response = organizationService.getHRsByOrganizationName(organizationName);
        if (response == null) {
            return ResponseEntity.noContent().build(); // No organization or HRs found
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/hrs/{hrId}")
    public ResponseEntity<String> deleteHRById(@PathVariable Long hrId) {
        organizationService.deleteHRById(hrId);
        return ResponseEntity.ok("HR record deleted. Organization checked for removal.");
    }

    @DeleteMapping("/{organizationId}")
    public ResponseEntity<String> deleteByOrganizationId(@PathVariable int organizationId) {
        organizationService.deleteByOrganizationId(organizationId);
        return ResponseEntity.ok("Organization and associated HRs deleted successfully");
    }

    @PatchMapping("/{organizationId}")
    public ResponseEntity<String> updateOrganization(
            @PathVariable Long organizationId,
            @RequestBody @Valid UpdateOrganizationRequest request) {
        return ResponseEntity.ok(organizationService.updateOrganization(organizationId, request));
    }

    @PatchMapping("/hr/{hrId}")
    public ResponseEntity<String> updateHR(
            @PathVariable Long hrId,
            @RequestBody @Valid UpdateOrganizationRequest request) {
        return ResponseEntity.ok(organizationService.updateHR(hrId, request));
    }

    @PatchMapping("/{organizationId}/{hrId}")
    public ResponseEntity<String> updateOrganizationAndHR(
            @PathVariable Long organizationId,
            @PathVariable Long hrId,
            @RequestBody @Valid UpdateOrganizationRequest request) {
        return ResponseEntity.ok(organizationService.updateOrganizationAndHR(organizationId, hrId, request));
    }

    @PostMapping("/hr/{organizationId}")
    public ResponseEntity<String> addHRToOrganization(@RequestBody @Valid AddHrRequest request, @PathVariable Long organizationId) {
        String response = organizationService.addHRToOrganization(request, organizationId);
        return ResponseEntity.ok(response);
    }
}
