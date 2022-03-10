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
	
	public Optional<List<Article>> findByUSER(User user) {
		return favoritesRepository.findByUSER(user);
	}
	
	public void save(Favorites fav) {
		favoritesRepository.save(fav);
	}

}
