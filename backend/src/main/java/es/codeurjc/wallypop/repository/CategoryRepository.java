package es.codeurjc.wallypop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.wallypop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
