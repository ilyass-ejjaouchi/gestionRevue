package com.revue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revue.beans.Referee;

@Repository
public interface RefereeRepository extends JpaRepository<Referee, Long>{
	Referee findByUsername(String username);
}
