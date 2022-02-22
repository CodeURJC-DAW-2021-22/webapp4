package es.codeurjc.wallypop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.wallypop.model.Article_Category;

public interface ArticleCategoryRepository extends JpaRepository<Article_Category, Long> {

}
