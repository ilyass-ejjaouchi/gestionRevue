package com.revue.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revue.beans.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
	@Query("select a from Article as a where  a.etatArticle ='POSTED'")
	List<Article> getPostedArticles();	
}
