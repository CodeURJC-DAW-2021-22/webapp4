package es.codeurjc.wallypop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Favorites;
import es.codeurjc.wallypop.model.User;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
	Favorites findByUSERSAndARTICLE(User user, Article article);
}
