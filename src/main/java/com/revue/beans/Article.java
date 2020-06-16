package com.revue.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revue.exceptions.BadRequestException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String resume;
	private String motCle;
	private String contenu;
	
	@Enumerated(EnumType.STRING)
	private EtatArticle etatArticle;

	@OneToMany(mappedBy = "article")
	@JsonIgnore
	private List<ArticleAuteurAsso> assos;
	
	@ManyToMany
	private List<Referee> referees;
	
	@OneToMany(mappedBy = "article")
	private List<Commentaire> commentaires;
	
	@ManyToOne
	@JsonIgnore
	private Revue revue;

	public Article(String resume, String motCle, String contenu, EtatArticle etatArticle) {
		super();
		this.resume = resume;
		this.motCle = motCle;
		this.contenu = contenu;
		this.etatArticle = etatArticle;
	}

	public void addReferee(Referee referee) {
		if (this.referees == null) {
			this.referees = new ArrayList<Referee>();
			this.referees.add(referee);
		}else if (this.referees.size() > 3) {
			throw new BadRequestException("l'article a 3 referees vous n'avez pas le droit d'ajouter le 4 ème");
		}
		this.referees.add(referee);
	}

	public void addCommentaire(Commentaire com) {
		if (this.commentaires == null) {
			this.commentaires = new ArrayList<Commentaire>();
			this.commentaires.add(com);
		}
		this.commentaires.add(com);
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", resume=" + resume + ", motCle=" + motCle + ", contenu=" + contenu
				+ ", etatArticle=" + etatArticle + "]";
	}

}
