package com.revue.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revue.beans.Article;
import com.revue.beans.ArticleAuteurAsso;
import com.revue.beans.Auteur;

@Repository
public interface ArticleAuteurAssoRepository extends JpaRepository<ArticleAuteurAsso, Long>{
	
List<ArticleAuteurAsso> findByArticle(Article article);
List<ArticleAuteurAsso> findByAuteur(Auteur auteur);

}
