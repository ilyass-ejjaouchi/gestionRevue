package com.revue.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ArticleAuteurAsso {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="idArticle")
	private Article article;
	
	@ManyToOne
	@JoinColumn(name="idAuteur")
	private Auteur auteur;
	
	private boolean isAuteurCorrespondant;

	public ArticleAuteurAsso(Article article, Auteur auteur, boolean isAuteurCorrespondant) {
		super();
		this.article = article;
		this.auteur = auteur;
		this.isAuteurCorrespondant = isAuteurCorrespondant;
	}

}
