package es.codeurjc.wallypop.repository;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ArticleRepository extends JpaRepository<Article, Long>, CrudRepository<Article, Long> {
    Page<Article> findAll(Pageable pageable);

    List<Article> findAll();

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
