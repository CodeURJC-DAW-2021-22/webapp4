package es.codeurjc.wallypop.controller.api;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Favorites;
import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.service.ArticleService;
import es.codeurjc.wallypop.service.FavoritesService;
import es.codeurjc.wallypop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesRestController {

    @Autowired
    private FavoritesService favoritesService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/{idUser}")
    public ResponseEntity<List<Favorites>> getFavorites(@PathVariable long idUser) {

        Optional<User> us = userService.findById(idUser);
        if (us.isPresent()) {
            return new ResponseEntity<>(us.get().getFAVORITES(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{idUser}/{idArticle}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Favorites> createFavorites(@PathVariable long idUser, @PathVariable long idArticle) {
        Optional<User> us = userService.findById(idUser);
        Optional<Article> art = articleService.findById(idArticle);
        Favorites favorites = favoritesService.findByUSERAndARTICLE(us.get(), art.get());
        if (us.isPresent() && art.isPresent()) {
            Favorites fav = new Favorites(us.get(), art.get());
            if (favorites != null) {
                favoritesService.delete(favorites);
            } else {
                favoritesService.save(fav);
            }
            return new ResponseEntity<>(fav, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
