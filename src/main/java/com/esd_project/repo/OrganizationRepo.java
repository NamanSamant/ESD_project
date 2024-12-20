package com.esd_project.repo;

import com.esd_project.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepo extends JpaRepository<Organization, Long> {
    Optional<Organization> findByName(String name);
    @Query("SELECT o FROM Organization o")
    List<Organization> findAllWithHR();
    void deleteById(int id);
}
