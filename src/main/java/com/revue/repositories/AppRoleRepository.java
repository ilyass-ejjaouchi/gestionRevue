package com.revue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revue.config.security.Roles;
import com.revue.config.security.dto.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
	AppRole findByRole(Roles roleName);
}
