package com.revue.beans;

import javax.persistence.Entity;
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
public class MembreComite {
	@Id
	private String username;
	private String nom;
	private String prenom;
	
	@ManyToOne
	@JsonIgnore
	private Revue revue;

	public MembreComite(String username, String nom, String prenom) {
		super();
		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
	}

}
