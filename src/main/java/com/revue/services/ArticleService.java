package com.revue.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revue.beans.Article;
import com.revue.beans.ArticleAuteurAsso;
import com.revue.beans.Auteur;
import com.revue.beans.Commentaire;
import com.revue.beans.EtatArticle;
import com.revue.exceptions.BadRequestException;
import com.revue.repositories.ArticleAuteurAssoRepository;
import com.revue.repositories.ArticleRepository;
import com.revue.repositories.AuteurRepository;
import com.revue.repositories.CommentaireRepository;
import com.revue.repositories.RefereeRepository;
import com.revue.repositories.RevueRepository;
import com.revue.rest.representation.ArticleRepresentationAuthentifie;
import com.revue.rest.representation.ArticleRepresentationNonAuthentifie;


@Service
@Transactional
public class ArticleService {
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
	ArticleAuteurAssoRepository articleAuteurAssoRepo;
	@Autowired
	RevueService revueService;

	public List<ArticleRepresentationNonAuthentifie> getSomeInfoOfArticlesPosted(String nomAuteur, String motCle) {
		List<ArticleRepresentationNonAuthentifie> articlesList = new ArrayList<ArticleRepresentationNonAuthentifie>();
		List<Article> articles = articleRepo.getPostedArticles();
			for (Article article : articles) {
				ArticleRepresentationNonAuthentifie articleItem = new ArticleRepresentationNonAuthentifie();
				List<ArticleAuteurAsso> auteurs = articleAuteurAssoRepo.findByArticle(article);
				List<Auteur> coAuteurs = new ArrayList<Auteur>();
					for (ArticleAuteurAsso a : auteurs) {
						if (a.isAuteurCorrespondant()) {
							a.getAuteur().setAuteurCorrespondant(true);
							articleItem.setAuteurCorrespondant(a.getAuteur());
						}else {
							coAuteurs.add(a.getAuteur());
						}
					}
				articleItem.setCoAuteurs(coAuteurs);
				articleItem.setMotCle(article.getMotCle());
				articleItem.setResume(article.getResume());
				
				if (nomAuteur != null ) {
					if (articleItem.getAuteurCorrespondant().getNom().equals(nomAuteur) ) {
						articlesList.add(articleItem);
					}
				}else if (motCle != null) {
					if (articleItem.getMotCle().contains(motCle)) {
						articlesList.add(articleItem);
					}
				}else{
					articlesList.add(articleItem);
				}
			}
			
		
	 return articlesList;
	}
	
	public List<ArticleRepresentationAuthentifie> getArticlesPosted() {
		List<ArticleRepresentationAuthentifie> articlesList = new ArrayList<ArticleRepresentationAuthentifie>();
		List<Article> articles = articleRepo.getPostedArticles();
			for (Article article : articles) {
				ArticleRepresentationAuthentifie articleItem = new ArticleRepresentationAuthentifie();
				List<ArticleAuteurAsso> auteurs = articleAuteurAssoRepo.findByArticle(article);
				List<Auteur> coAuteurs = new ArrayList<Auteur>();
					for (ArticleAuteurAsso a : auteurs) {
						if (a.isAuteurCorrespondant()) {
							a.getAuteur().setAuteurCorrespondant(true);
							articleItem.setAuteurCorrespondant(a.getAuteur());
						}else {
							coAuteurs.add(a.getAuteur());
						}
					}
				articleItem.setCoAuteurs(coAuteurs);
				articleItem.setMotCle(article.getMotCle());
				articleItem.setResume(article.getResume());
				articleItem.setContenu(article.getContenu());
				articlesList.add(articleItem);
				}
	 return articlesList;
	}
	public void ajouterArticle(String resume,String motCle, String contenu, Long idRevue,String usernameAuteur) {
		Article article = new Article(resume, motCle, contenu, EtatArticle.EN_VUE_DE_PUBLICATION);
		if (auteurRepo.findByUsername(usernameAuteur) != null) {
			Auteur auteur = auteurRepo.findByUsername(usernameAuteur);
			article.setRevue(revueService.getRevue(idRevue));
			articleRepo.save(article); 
			ArticleAuteurAsso asso = new ArticleAuteurAsso(article, auteur, true);
			articleAuteurAssoRepo.save(asso);
		}else {
			throw new BadRequestException("l'auteur correspondant n'existe pas");
		}
		
	}
	public void ajouterCoAuteurToArticle(String usernameCoAuteur,Long idArticle) {
		Auteur coAuteur = auteurRepo.findByUsername(usernameCoAuteur);
		Article article = getArticle(idArticle);
		ArticleAuteurAsso asso = new ArticleAuteurAsso(article, coAuteur, false);
		articleAuteurAssoRepo.save(asso);
	}
	public Article modifierArticle(Long id,String newResume,String newMotCle, String newContenu) {
			Article a = getArticle(id);
			if (newResume != null) {
				a.setResume(newResume);
			}
			if (newMotCle != null) {
				a.setMotCle(newMotCle);
			}
			if (newContenu != null) {
				a.setContenu(newContenu);
			}
			return a;
	}
	public Article supprimerArticle(Long id) {
		Article a = getArticle(id);
		List<ArticleAuteurAsso> asso = articleAuteurAssoRepo.findByArticle(a);
		for (ArticleAuteurAsso ass : asso) {
			articleAuteurAssoRepo.deleteById(ass.getId());
		}
		if (a.getEtatArticle() == EtatArticle.EN_VUE_DE_PUBLICATION) {
			 articleRepo.deleteById(id);
			 return a;
		}
		else {
			throw new BadRequestException("vous n'avez pas le droit de supprimer cet "
									+ "article car le le processus d’évaluation a étè commencé");
		}
		
	}
	public List<Auteur> getAuteursByArticle(Article article){
		List<ArticleAuteurAsso> articleAuteurAsso = articleAuteurAssoRepo.findByArticle(article);
		List<Auteur> auteurs = new ArrayList<Auteur>();
		for (ArticleAuteurAsso asso : articleAuteurAsso) {
			if (asso.isAuteurCorrespondant()) {
				asso.getAuteur().setAuteurCorrespondant(true);
				auteurs.add(asso.getAuteur());
			}else {
				auteurs.add(asso.getAuteur());	
			}
		}
		return auteurs;
	}
	public  EtatArticle suivreArticle(String username) {
		Auteur a = auteurRepo.findByUsername(username);
		List<ArticleAuteurAsso> asso = articleAuteurAssoRepo.findByAuteur(a);
		if (!asso.isEmpty()) {
			return articleAuteurAssoRepo.findByAuteur(a).get(0).getArticle().getEtatArticle();
		}else {
			throw new BadRequestException("l'auteur n'a pas d'article pour l'instant");
		}
		
	}
	public Article getArticle(Long id) {
		if (articleRepo.findById(id).isPresent()) {
			return articleRepo.findById(id).get();
		}else {
			throw new BadRequestException("l'article n'existe pas");
		}
	}
	
	public List<Commentaire> visualiserRepportReferees(Long idArticle) {
		Article a = getArticle(idArticle);
		return commentaireRepo.findByArticle(a);
	}
	
	public Article qualifierArticle(Long idArticle, EtatArticle qualification) {
		Article a = getArticle(idArticle);
		a.setEtatArticle(qualification);
		return articleRepo.save(a);
	}
}

