package com.revue.beans;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Commentaire {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JsonIgnore
	private Referee referee;
	
	@ManyToOne
	@JsonIgnore
	private Article article;
	
	private String remarque;
	
	@Enumerated(EnumType.STRING)
	private QualificationArticle qualification;

	public Commentaire(Referee referee, Article article, String remarque, QualificationArticle qualification) {
		super();
		this.referee = referee;
		this.article = article;
		this.remarque = remarque;
		this.qualification = qualification;
	}


}
