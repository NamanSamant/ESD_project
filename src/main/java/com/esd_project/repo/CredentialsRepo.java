package com.esd_project.repo;

import com.esd_project.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsRepo extends JpaRepository<Credentials, Integer> {
    Credentials findByEmail(String email);
}
