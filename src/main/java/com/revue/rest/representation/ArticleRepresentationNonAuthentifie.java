package com.revue.rest.representation;

import java.util.List;

import com.revue.beans.Auteur;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ArticleRepresentationNonAuthentifie {
	private String resume;
	private String motCle;
	private Auteur auteurCorrespondant;
	private List<Auteur> coAuteurs;
	
}
