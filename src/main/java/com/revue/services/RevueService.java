package com.revue.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revue.beans.MembreComite;
import com.revue.beans.Revue;
import com.revue.config.security.Roles;
import com.revue.exceptions.BadRequestException;
import com.revue.repositories.AppUserRepository;
import com.revue.repositories.MembreComiteRepository;
import com.revue.repositories.RevueRepository;

@Service
@Transactional
public class RevueService {
	@Autowired
	RevueRepository revueRepo;
	@Autowired
	MembreComiteRepository membreComiteRepo;
	@Autowired
	AccountService accountService;
	@Autowired 
	AppUserRepository appUserRepo;
	
	public Revue ajouterRevue(String politique) {
		Revue r = new Revue(politique);
		return revueRepo.save(r);
	}
	public Revue getRevue(Long id) {
		if (revueRepo.findById(id).isPresent()) {
			return revueRepo.findById(id).get();
		}else {
			throw new BadRequestException("la revue n'existe pas");
		}
	}
	
	public MembreComite ajouterMembreComiteEditorial(String username,String nom, String prenom, Long idRevue) {
		MembreComite  membre = new MembreComite(username, nom, prenom);
		if (appUserRepo.findByUsername(membre.getUsername()) == null) {
			throw new BadRequestException("le username n'existe pas");
		}
		membre.setRevue(getRevue(idRevue));
		if (membreComiteRepo.findByUsername(membre.getUsername()) == null) {
			accountService.addRoleToUser(username, Roles.MEMBRE_COMITE_EDITORIAL);
		}else {
			throw new BadRequestException("le membre de comité existe dejà");
		}
		return membreComiteRepo.save(membre);
	}
}

