package es.codeurjc.wallypop.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Category;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	Page<Article> findAllPageable(Pageable pageable);
	
	Page<Article> findByTitleContaining (String title, Pageable pageable);
	
	List<Article> findByCATEGORYS(Category cat);

	List<Article> findByCITYContainingIgnoreCaseAndSOLDFalse(String city);

	List<Article> findBySOLDFalse();

	List<Article> findBySOLDTrue();

	List<Article> findByTITLEContainingIgnoreCaseAndCITYContainingIgnoreCaseAndSOLDFalseOrDESCRIPTIONContainingIgnoreCaseAndCITYContainingIgnoreCaseAndSOLDFalse(
			String title, String city, String description, String city2);

	List<Article> findByTITLEContainingIgnoreCaseAndSOLDFalseOrDESCRIPTIONContainingIgnoreCaseAndSOLDFalse(String title,
			String description);

	List<Article> findByTITLEContainingIgnoreCaseOrDESCRIPTIONContainingIgnoreCaseOrCITYContainingIgnoreCaseAndSOLDFalse(
			String title, String description, String city);
}
