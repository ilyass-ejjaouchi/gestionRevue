package com.revue.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Revue {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String politique;
	
	@OneToMany(mappedBy = "revue")
	private List<MembreComite> membresComite;
	
	@OneToMany(mappedBy = "revue")
	private List<Article> articles;

	public Revue(String politique) {
		super();
		this.politique = politique;
	}
	
	public void addMembreToComite(MembreComite membre ) {
		if (this.membresComite == null) {
			this.membresComite = new ArrayList<MembreComite>();
			this.membresComite.add(membre);
		}
		this.membresComite.add(membre);
	}
	
}
