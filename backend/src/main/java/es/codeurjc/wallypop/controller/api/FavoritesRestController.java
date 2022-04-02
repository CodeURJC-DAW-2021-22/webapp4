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

    public static ResponseEntity<Favorites> getFavoritesResponseEntity(@PathVariable long idArticle, Optional<User> us, ArticleService articleService, FavoritesService favoritesService) {
        Optional<Article> art = articleService.findById(idArticle);
        Favorites favorites = favoritesService.findByUSERAndARTICLE(us.get(), art.get());
        if (art.isPresent()) {
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

    @GetMapping("")
    public ResponseEntity<List<Favorites>> myFavorites(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            return new ResponseEntity<>(userService.findByNAME(principal.getName()).get().getFAVORITES(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{idArticle}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Favorites> createFavorites(HttpServletRequest request, @PathVariable long idArticle) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            Optional<User> us = userService.findByNAME(principal.getName());
            return getFavoritesResponseEntity(idArticle, us, articleService, favoritesService);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @DeleteMapping("/{idArticle}")
    public ResponseEntity<Favorites> deleteFavorites(HttpServletRequest request, @PathVariable long idArticle) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
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
