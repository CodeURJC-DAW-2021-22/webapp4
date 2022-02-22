package es.codeurjc.wallypop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.wallypop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
