package es.codeurjc.wallypop.controller.api;

import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Favorites;
import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.service.ArticleService;
import es.codeurjc.wallypop.service.FavoritesService;
import es.codeurjc.wallypop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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

    @GetMapping("/{idFavorite}/user")
    public ResponseEntity<User> getUserFavorite(@PathVariable long idFavorite) {
        User us = favoritesService.findById(idFavorite).get().getUSER();
        return new ResponseEntity<>(us, HttpStatus.OK);
    }

    @GetMapping("/{idFavorite}/article")
    public ResponseEntity<Article> getArtcileFavorite(@PathVariable long idFavorite) {
        Article article = favoritesService.findById(idFavorite).get().getARTICLE();
        return new ResponseEntity<>(article, HttpStatus.OK);
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

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
        Favorites favorite = favoritesService.findById(id).orElseThrow();
        if (favorite.getARTICLE().getPHOTO() != null) {

            Resource file = new InputStreamResource(favorite.getARTICLE().getPHOTO().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(favorite.getARTICLE().getPHOTO().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
