package es.codeurjc.wallypop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.wallypop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Optional<Category>findByTITLE(String TITLE);

}
