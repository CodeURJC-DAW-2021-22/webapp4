package es.codeurjc.wallypop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Favorites;
import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.service.ArticleService;
import es.codeurjc.wallypop.service.FavoritesService;
import es.codeurjc.wallypop.service.UserService;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesRestController {

	@Autowired
    private FavoritesService favoritesService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private ArticleService articleService;

    @GetMapping("/")
    List<Favorites> all() {
        return favoritesService.findAll();
    }

    @GetMapping("/{idUser}/{idArticle}")
    public ResponseEntity<Favorites> getFavorites(@PathVariable long idUser, @PathVariable long idArticle) {

    	Optional<User> us = userService.findById(idUser);
    	Optional<Article> art = articleService.findById(idArticle);
    	if (us.isPresent() && art.isPresent()) {
    		Favorites op = favoritesService.findByUSERAndARTICLE(us.get(), art.get());
            if (op != null) {
                return new ResponseEntity<>(op, HttpStatus.OK);
            }
    	}
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{idUser}/{idArticle}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Favorites> createFavorites(@PathVariable long idUser, @PathVariable long idArticle) {
    	Optional<User> us = userService.findById(idUser);
    	Optional<Article> art = articleService.findById(idArticle);
    	if (us.isPresent() && art.isPresent()) {
    		Favorites fav = new Favorites(us.get(),art.get());
    		favoritesService.save(fav);
            return new ResponseEntity<>(fav, HttpStatus.OK);
    	}
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{idUser}/{idArticle}")
    public ResponseEntity<Favorites> deleteFavorites(@PathVariable long idUser, @PathVariable long idArticle) {

    	Optional<User> us = userService.findById(idUser);
    	Optional<Article> art = articleService.findById(idArticle);
    	if (us.isPresent() && art.isPresent()) {
    		Favorites op = favoritesService.findByUSERAndARTICLE(us.get(), art.get());
    		favoritesService.delete(op);
            return new ResponseEntity<>(HttpStatus.OK);
    	}
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
