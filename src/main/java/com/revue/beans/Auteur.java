package com.revue.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Auteur {
	@Id
	private String username;
	private String nom;
	private String prenom;
	private String affiliation;
	@Transient
	private boolean isAuteurCorrespondant;
	
	@OneToMany(mappedBy = "auteur")
	@JsonIgnore
	private List<ArticleAuteurAsso> assos;

	public Auteur(String username, String nom, String prenom, String affiliation) {
		super();
		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
		this.affiliation = affiliation;
	}

	
}
