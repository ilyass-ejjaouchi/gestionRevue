package com.revue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revue.beans.MembreComite;

@Repository
public interface MembreComiteRepository extends JpaRepository<MembreComite, Long>{
	MembreComite findByUsername(String username);
}
