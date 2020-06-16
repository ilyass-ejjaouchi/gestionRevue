package com.revue.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revue.beans.Article;
import com.revue.beans.Auteur;
import com.revue.beans.Commentaire;
import com.revue.beans.EtatArticle;
import com.revue.rest.representation.ArticleRepresentationAuthentifie;
import com.revue.rest.representation.ArticleRepresentationNonAuthentifie;
import com.revue.services.ArticleService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class ArticleController {
	@Autowired 
	ArticleService articleService;
	
	private static final Logger logger = LogManager.getLogger(ArticleController.class);
	
	//n'importe quel utilisateur peut accéder à cet end point
	@GetMapping("/articlesPosted/someInfo")
	public List<ArticleRepresentationNonAuthentifie> getSomeInfoOfArticlesPosted(
			@RequestParam(name = "nomAuteur", required = false) String nomAuteur,
			@RequestParam(name = "motCle",required = false) String motCle) {
		return articleService.getSomeInfoOfArticlesPosted(nomAuteur, motCle);
	}
	
	@PreAuthorize("hasAnyAuthority({'USER','AUTEUR','ADMIN','REFEREE','MEMBRE_COMITE_EDITORIAL'})")
	@GetMapping("/articlesPosted")
	public List<ArticleRepresentationAuthentifie> getArticlesPosted() {
		return articleService.getArticlesPosted();
	}
	
	@PreAuthorize("hasAuthority('AUTEUR')")
	@GetMapping("/auteurs/{username}/articles")
	public EtatArticle suivreArticle(@PathVariable String username) {
		return articleService.suivreArticle(username);
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/articles/{idArticle}/auteurs")
	public List<Auteur> getAuteursByArticle(@PathVariable Long idArticle){
		Article a = articleService.getArticle(idArticle);
		return articleService.getAuteursByArticle(a);
	}

	@PreAuthorize("hasAuthority('AUTEUR')")
	@GetMapping("/articles/{idArticle}/Commentaires")
	public List<Commentaire> visualiserRepportReferees(@PathVariable Long idArticle) {
		return articleService.visualiserRepportReferees(idArticle);
	}
	
	@PreAuthorize("hasAuthority('AUTEUR')")
	@PostMapping("/auteurs/{usernameAuteur}/articles")
	public void addArticle(
			@PathVariable String usernameAuteur,
			@RequestParam(name = "idRevue") Long idRevue,
			@RequestParam(name = "resume", required = false) String resume,
			@RequestParam(name = "motCle", required = false) String motCle,
			@RequestParam(name = "contenu", required = false) String contenu) {
		articleService.ajouterArticle(resume, motCle, contenu, idRevue, usernameAuteur);
	}
	
	@PreAuthorize("hasAuthority('AUTEUR')")
	@PostMapping("/coAuteurs/{usernameCoAuteur}/articles/{idArticle}")
	public void addCoAuteurToArticle(
			@PathVariable String usernameCoAuteur,
			@PathVariable(name = "idArticle") Long idArticle) {
		articleService.ajouterCoAuteurToArticle(usernameCoAuteur, idArticle);
	}
	
	@PreAuthorize("hasAuthority('MEMBRE_COMITE_EDITORIAL')")
	@PostMapping("/articles/{idArticle}")
	public Article qualifierArticle(
			@PathVariable Long idArticle,
			@RequestParam(name = "qualification", required = false) EtatArticle qualification) {
		return articleService.qualifierArticle(idArticle, qualification);
	}

	@PreAuthorize("hasAuthority('AUTEUR')")
	@PutMapping("/articles/{idArticle}")
	public void updateArticle(
			@PathVariable Long idArticle,
			@RequestParam(name = "newResume", required = false) String newResume,
			@RequestParam(name = "newMotCle", required = false) String newMotCle,
			@RequestParam(name = "newContenu", required = false) String newContenu) {
		Article articleModified = articleService.modifierArticle(idArticle, newResume, newMotCle, newContenu);
		logger.info("l'article " + articleModified.getId() +" a étè modifié voila les nouveaux informations");
		logger.info(articleModified.toString());
	}
	
	@PreAuthorize("hasAuthority('AUTEUR')")
	@DeleteMapping("/articles/{id}")
	public void deleteArticle(@PathVariable Long id) {
		 Article a = articleService.supprimerArticle(id);
		 logger.info("l'article " + a.getId() +" a étè supprimé voila les anciens informations");
		logger.info(a.toString());
	}

}
