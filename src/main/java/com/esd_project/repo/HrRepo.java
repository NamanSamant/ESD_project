package com.esd_project.repo;

import com.esd_project.entity.HR;
import jakarta.transaction.Transactional;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HrRepo extends JpaRepository<HR, Long> {
    @Query("SELECT h FROM HR h WHERE h.organization.id = :organizationId")
    List<HR> findByOrganizationId(@Param("organizationId") int organizationId);
    @Modifying
    @Transactional
    @Query("DELETE FROM HR h WHERE h.organization.id = :organizationId")
    void deleteByOrganizationId(@Param("organizationId") int organizationId);

    boolean existsByOrganizationId(Long organizationId);
}
