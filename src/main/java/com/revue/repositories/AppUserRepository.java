package com.revue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revue.config.security.dto.AppUser;


public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	AppUser findByUsername(String username);
}
