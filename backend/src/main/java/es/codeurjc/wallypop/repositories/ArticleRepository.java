package es.codeurjc.wallypop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.wallypop.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	

}
