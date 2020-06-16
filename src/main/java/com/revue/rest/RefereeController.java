package com.revue.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revue.beans.QualificationArticle;
import com.revue.beans.Referee;
import com.revue.services.ArticleService;
import com.revue.services.RefereeService;

@RestController
public class RefereeController {
	@Autowired 
	ArticleService articleService;
	@Autowired
	RefereeService refereeService;
	
	@PreAuthorize("hasAnyAuthority({'ADMIN','MEMBRE_COMITE_EDITORIAL'})")
	@PostMapping("/referees")
	public void addReferee(
			@RequestParam String usernameReferee,
			@RequestParam String nom,
			@RequestParam String prenom,
			@RequestParam String specialite
			) {
		refereeService.addReferee(usernameReferee, nom, prenom, specialite);
	}
	
	@PreAuthorize("hasAnyAuthority({'ADMIN','MEMBRE_COMITE_EDITORIAL'})")
	@GetMapping("/referees/{usernameReferee}")
	public Referee getReferee(@PathVariable String usernameReferee){
		return refereeService.getReferee(usernameReferee);
	}
	
	@PreAuthorize("hasAnyAuthority({'ADMIN','MEMBRE_COMITE_EDITORIAL'})")
	@PostMapping("/articles/{idArticle}/referees/{usernameReferee}")
	public void affecterRefereeToArticle(
			@PathVariable Long idArticle,
			@PathVariable String usernameReferee) {
		refereeService.affecterRefereeToArticle(usernameReferee, idArticle);
	}
	
	@PreAuthorize("hasAuthority('REFEREE')")
	@GetMapping("/articles/{idArticle}/manuscrit")
	public String seeManuscritOfArticle(@PathVariable Long idArticle) {
		return refereeService.seeManuscritOfArticle(idArticle);
	}
	
	@PreAuthorize("hasAuthority('REFEREE')")
	@PostMapping("/articles/{idArticle}/referees/{usernameReferee}/commentaires")
	public void evaluerArticle(
			@PathVariable Long idArticle,
			@PathVariable String usernameReferee,
			@RequestParam(name = "qualification", required = false) QualificationArticle qualification,
			@RequestParam(name = "remarque", required = false) String remarque
			){
		refereeService.evaluerArticle(idArticle, usernameReferee, qualification, remarque);
	}
}

