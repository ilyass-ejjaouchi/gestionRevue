package com.revue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revue.beans.Auteur;

@Repository
public interface AuteurRepository extends JpaRepository<Auteur, Long>{
	Auteur findByUsername(String username);
}
