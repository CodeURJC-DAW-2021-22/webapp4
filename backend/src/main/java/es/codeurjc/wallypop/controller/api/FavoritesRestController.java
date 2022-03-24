package es.codeurjc.wallypop.controller.api;

import java.security.Principal;
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

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesRestController {

	@Autowired
    private FavoritesService favoritesService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private ArticleService articleService;

	@GetMapping("")
	public ResponseEntity<List<Favorites>> myFavorites(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if(principal != null) {
			return new ResponseEntity<>(userService.findByNAME(principal.getName()).get().getFAVORITES(), HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{idArticle}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Favorites> createFavorites(HttpServletRequest request, @PathVariable long idUser, @PathVariable long idArticle) {
		Principal principal = request.getUserPrincipal();
		if(principal != null) {
			Optional<User> us = userService.findById(idUser);
			Optional<Article> art = articleService.findById(idArticle);
			if (art.isPresent()) {
				Favorites fav = new Favorites(us.get(),art.get());
				favoritesService.save(fav);
				return new ResponseEntity<>(fav, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

	}

    @DeleteMapping("/{idArticle}")
    public ResponseEntity<Favorites> deleteFavorites(HttpServletRequest request, @PathVariable long idArticle) {
		Principal principal = request.getUserPrincipal();
		if(principal != null) {
			User us = userService.findByNAME(principal.getName()).get();
			Optional<Article> art = articleService.findById(idArticle);
			if (art.isPresent()) {
				Favorites op = favoritesService.findByUSERAndARTICLE(us, art.get());
				favoritesService.delete(op);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
    }
}
