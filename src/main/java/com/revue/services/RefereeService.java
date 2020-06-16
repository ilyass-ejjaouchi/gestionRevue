package com.revue.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revue.beans.Article;
import com.revue.beans.Commentaire;
import com.revue.beans.EtatArticle;
import com.revue.beans.QualificationArticle;
import com.revue.beans.Referee;
import com.revue.config.security.Roles;
import com.revue.exceptions.BadRequestException;
import com.revue.repositories.AppUserRepository;
import com.revue.repositories.ArticleRepository;
import com.revue.repositories.AuteurRepository;
import com.revue.repositories.CommentaireRepository;
import com.revue.repositories.RefereeRepository;
import com.revue.repositories.RevueRepository;

@Service
@Transactional
public class RefereeService {
	@Autowired 
	ArticleRepository articleRepo;
	@Autowired 
	AuteurRepository auteurRepo;
	@Autowired
	CommentaireRepository commentaireRepo;
	@Autowired
	RefereeRepository refereeRepo;
	@Autowired
	RevueRepository revueRepo;
	@Autowired
	ArticleService articleService;
	@Autowired 
	AppUserRepository appUserRepo;
	@Autowired
	AccountService accountService;
	
	public Referee addReferee(String username, String nom, String prenom, String specialite) {
		Referee referee = new Referee(username, nom, prenom, specialite);
		if (appUserRepo.findByUsername(referee.getUsername()) == null) {
			throw new BadRequestException("le username n'existe pas");
		}	
		if (refereeRepo.findByUsername(referee.getUsername()) == null) {
			accountService.addRoleToUser(username, Roles.REFEREE);
		}else {
			throw new BadRequestException("le referee existe dejà"); 
		}
		return refereeRepo.save(referee);
	}
	public Referee getReferee(String usernameReferee) {
		if (refereeRepo.findByUsername(usernameReferee) == null) {
			throw new BadRequestException("le referee n'existe pas");
		}else {
			return refereeRepo.findByUsername(usernameReferee);
		}
	}
	
	public void affecterRefereeToArticle(String usernameReferee, Long idArticle) {
		Referee r = getReferee(usernameReferee);
		Article a = articleService.getArticle(idArticle);
		a.setEtatArticle(EtatArticle.ENCOURS_DEVALUATION);
		a.addReferee(r);
		articleRepo.save(a);
	}
	
	public String seeManuscritOfArticle(Long idArticle) {
		return articleService.getArticle(idArticle).getContenu();
	}
	public Commentaire evaluerArticle(Long idArticle, String usernameReferee, QualificationArticle qualification, String remarque) {
		Article a = articleService.getArticle(idArticle);
		Referee r = getReferee(usernameReferee);
		if (a.getReferees().contains(r)) {
			Commentaire comm = new Commentaire(r, a, remarque, qualification);
			return commentaireRepo.save(comm);
		}else {
			throw new BadRequestException("vous n'etes pas un referee de cet article");
		}
		
	}
	
}

