package es.codeurjc.wallypop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Favorites;
import es.codeurjc.wallypop.model.User;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
	Optional<List<Article>> findByUSER(User user);

}
