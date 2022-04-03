package es.codeurjc.wallypop.repository;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Favorites;
import es.codeurjc.wallypop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    Favorites findByUSERSAndARTICLE(User user, Article article);
}
