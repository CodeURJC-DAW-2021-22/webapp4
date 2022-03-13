package es.codeurjc.wallypop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.wallypop.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	/*Optional<List<Article>> findByReserved(boolean RESERVED);
	Optional<List<Article>> findBySold(boolean SOLD);
	Optional<List<Article>> findByUser(User USER);*/
	List<Article> findByTITLEOrDESCRIPTIONContaining(String title, String description);
}
