package es.codeurjc.wallypop.service;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Favorites;
import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.repository.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepository;

    public void delete(Favorites favorites) {
        favoritesRepository.delete(favorites);
    }

    public void delete(long id_favorite) {
        favoritesRepository.deleteById(id_favorite);
    }

    public boolean favoriteExists(long id_favorite) {
        return favoritesRepository.findById(id_favorite).isPresent();
    }

    public List<Favorites> findAll() {
        return favoritesRepository.findAll();
    }

    public Optional<Favorites> findById(long id) {
        return favoritesRepository.findById(id);
    }

    public Favorites findByUSERAndARTICLE(User user, Article article) {
        return favoritesRepository.findByUSERSAndARTICLE(user, article);
    }

    public Favorites getByUSERandARTICLE(long id_favorite) {
        return favoritesRepository.findById(id_favorite).get();
    }

    public void save(Favorites fav) {
        favoritesRepository.save(fav);
    }
}
