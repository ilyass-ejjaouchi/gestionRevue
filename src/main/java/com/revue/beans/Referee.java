package com.revue.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Referee {
	@Id 
	private String username;
	private String nom;
	private String prenom;
	private String specialite;
	
	@OneToMany(mappedBy = "referee")
	private List<Commentaire> commentaires;

	public Referee(String username, String nom, String prenom, String specialite) {
		super();
		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
		this.specialite = specialite;
	}
	

}
