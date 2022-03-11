package es.codeurjc.wallypop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.User;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	/*Optional<List<Article>> findByReserved(boolean RESERVED);
	Optional<List<Article>> findBySold(boolean SOLD);
	Optional<List<Article>> findByUser(User USER);*/
}
