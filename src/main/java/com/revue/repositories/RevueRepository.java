package com.revue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revue.beans.Revue;

@Repository
public interface RevueRepository extends JpaRepository<Revue, Long>{

}
