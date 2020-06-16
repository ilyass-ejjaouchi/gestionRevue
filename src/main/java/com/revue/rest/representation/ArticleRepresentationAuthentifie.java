package com.revue.rest.representation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ArticleRepresentationAuthentifie extends ArticleRepresentationNonAuthentifie {
	private String contenu;
}
