package es.codeurjc.wallypop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Favorites;
import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.repository.FavoritesRepository;

@Service
public class FavoritesService {

	@Autowired
	private FavoritesRepository favoritesRepository;
	
	public List<Favorites> findAll() {
		return favoritesRepository.findAll();
	}
	
	public void save(Favorites fav) {
		favoritesRepository.save(fav);
	}

	public boolean favoriteExists(long id_favorite) {
		return favoritesRepository.findById(id_favorite).isPresent();
	}
	
	public void delete(long id_favorite) {
		favoritesRepository.deleteById(id_favorite);
	}
	
	public Favorites getByUSERandARTICLE(long id_favorite) {
		return favoritesRepository.findById(id_favorite).get();
	}
}
