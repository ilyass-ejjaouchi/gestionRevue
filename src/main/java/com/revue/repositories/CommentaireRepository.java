package com.revue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revue.beans.Commentaire;
import com.revue.beans.Article;
import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long>{
	List<Commentaire> findByArticle(Article article);
}
