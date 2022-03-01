package es.codeurjc.wallypop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import antlr.collections.List;
import es.codeurjc.wallypop.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	
	//@Query("SELECT * FROM ARTICLE")
	//public 	List getListArticles (String ID_USER);
	
}
