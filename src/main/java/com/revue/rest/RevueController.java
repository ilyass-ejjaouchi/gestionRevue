package com.revue.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revue.beans.Revue;
import com.revue.services.RevueService;

@RestController
public class RevueController {
	@Autowired
	RevueService revueService;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/revues")
	public Revue addRevue(@RequestParam(name = "politique", required = false) String politique) {
		return revueService.ajouterRevue(politique);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/revues/{idRevue}")
	public Revue getRevue(@PathVariable Long idRevue){
		return revueService.getRevue(idRevue);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/revues/{idRevue}/comiteEditorial")
	public void addMembreComiteEditorial(
			@PathVariable Long idRevue,
			@RequestParam(name = "usernameMembre", required = false) String usernameMembre,
			@RequestParam(name = "nom", required = false) String nom,
			@RequestParam(name = "prenom", required = false) String prenom
			) {
		revueService.ajouterMembreComiteEditorial(usernameMembre, nom, prenom, idRevue);
	}
	
	
}
