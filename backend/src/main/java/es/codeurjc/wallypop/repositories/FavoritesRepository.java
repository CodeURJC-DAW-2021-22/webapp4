package es.codeurjc.wallypop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.wallypop.model.Favorites;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

}