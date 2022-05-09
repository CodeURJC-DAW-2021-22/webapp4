package es.codeurjc.wallypop.controller.api;

import es.codeurjc.wallypop.dto.ArticleRequest;
import es.codeurjc.wallypop.model.Article;
import es.codeurjc.wallypop.model.Category;
import es.codeurjc.wallypop.model.Favorites;
import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.service.ArticleService;
import es.codeurjc.wallypop.service.CategoryService;
import es.codeurjc.wallypop.service.MapService;
import es.codeurjc.wallypop.service.UserService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.*;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/articles")
public class ArticleRestController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    List<Article> all() {
        return articleService.findAll();
    }

    @GetMapping(params = {"page"})
    public ResponseEntity<List<Article>> articlesPagination(HttpServletRequest request, @RequestParam("page") int page) {
        if (page != -1) { // with pagination
            int pageSize = 4;
            try {
                List<Article> lArticlesPageable = new LinkedList<>();
                Pageable paging = PageRequest.of(0, pageSize);
                Page<Article> articlePage;
                articlePage = articleService.findAllPageable(paging.withPage(page));
                if (articlePage.getNumberOfElements() == 0) {
                    // Empty
                } else {
                   for (Article a : articlePage) {
                       lArticlesPageable.add(a);
                   }
                }
                return new ResponseEntity<>(lArticlesPageable, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else { // without pagination
            try {
                return new ResponseEntity<>(articleService.findAll(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/me")
    public ResponseEntity<List<Article>> me(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            User user = userService.findByNAME(principal.getName()).get();
            return new ResponseEntity<>(user.getARTICLES(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable long id) {

        Optional<Article> op = articleService.findById(id);
        if (op.isPresent()) {
            Article article = op.get();
            return new ResponseEntity<>(article, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserArticle(@PathVariable long id) {

        Optional<User> op = userService.findById(articleService.findById(id).get().getUserID());
        if (op.isPresent()) {
            User user = op.get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
        Article article = articleService.findById(id).orElseThrow();
        if (article.getPHOTO() != null) {

            Resource file = new InputStreamResource(article.getPHOTO().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(article.getPHOTO().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Article> createArticle(HttpServletRequest request, @RequestBody ArticleRequest articleRequest) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            Article article = new Article();
            article.setUSER(userService.findByNAME(principal.getName()).get());
            return getArticleResponseEntity(articleRequest, article);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> createArticle(HttpServletRequest request, @RequestBody ArticleRequest articleRequest, @PathVariable long id) {
        Principal principal = request.getUserPrincipal();
        if (principal != null && articleService.findById(id).isPresent()) {
            Article article = articleService.findById(id).get();
            User user = userService.findByNAME(principal.getName()).get();
            if (article.getUserID() == user.getID_USER() || user.isIS_ADMIN()) {
                return getArticleResponseEntity(articleRequest, article);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
            throws IOException {

        Article article = articleService.findById(id).orElseThrow();

        URI location = fromCurrentRequest().build().toUri();

        article.setPHOTO(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        articleService.save(article);

        return ResponseEntity.created(location).build();
    }

    private ResponseEntity<Article> getArticleResponseEntity(@RequestBody ArticleRequest articleRequest, Article article) {
        article.setTITLE(articleRequest.getTITLE());
        article.setDESCRIPTION(articleRequest.getDESCRIPTION());
        article.setCITY(articleRequest.getCITY());
        article.setPOSTAL_CODE(articleRequest.getPOSTAL_CODE());
        article.setPRICE(articleRequest.getPRICE());
        for (int i : articleRequest.getCategories()) {
            Optional<Category> c = categoryService.findById(i);
            if (c.isPresent()) {
                article.addCategory(c.get());
            }
        }
        articleService.save(article);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Article> deleteArticle(HttpServletRequest request, @PathVariable long id) {
        try {
            Principal principal = request.getUserPrincipal();
            if (principal != null && articleService.findById(id).isPresent()) {
                Article article = articleService.findById(id).get();
                User user = userService.findByNAME(principal.getName()).get();
                if (article.getUserID() == user.getID_USER() || user.isIS_ADMIN()) {
                    articleService.deleteById(id);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<Object> deleteImage(@PathVariable long id) throws IOException {

        Article article = articleService.findById(id).orElseThrow();

        article.setPHOTO(null);

        articleService.save(article);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/search", params = {"query", "city"})
    public ResponseEntity<List<Article>> search(@RequestParam("query") String query, @RequestParam("city") String city) {
        List<Article> lArticles;
        if (!city.equals("") && !query.equals("")) {
            lArticles = articleService
                    .findByTITLEContainingIgnoreCaseAndCITYContainingIgnoreCaseAndSOLDFalseOrDESCRIPTIONContainingIgnoreCaseAndCITYContainingIgnoreCaseAndSOLDFalse(
                            query, city);
        } else if (city.equals("")) {
            lArticles = articleService
                    .findByTITLEContainingIgnoreCaseAndSOLDFalseOrDESCRIPTIONContainingIgnoreCaseAndSOLDFalse(query);
        } else if (query.equals("")) {
            lArticles = articleService.findByCITYContainingIgnoreCaseAndSOLDFalse(city);
        } else {
            lArticles = articleService.findAll();
        }
        return ResponseEntity.ok(lArticles);
    }

    @PostMapping("/{idArticle}/reserve")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Article> reserveArticle(@PathVariable long idArticle) {
        Optional<Article> article = articleService.findById(idArticle);
        if (article.isPresent()) {
            Article art = article.get();
            if (art.isRESERVED()) {
                art.setRESERVED(false);
            } else {
                art.setRESERVED(true);
            }
            articleService.save(art);
            return new ResponseEntity<>(art, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{idArticle}/sell")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Article> sellArticle(@PathVariable long idArticle) {
        Optional<Article> article = articleService.findById(idArticle);
        if (article.isPresent()) {
            Article art = article.get();
            if (art.isRESERVED()) {
                art.setRESERVED(false);
            }
            if (art.isSOLD()) {
                art.setSOLD(false);
            } else {
                art.setSOLD(true);
            }
            articleService.save(art);
            return new ResponseEntity<>(art, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/map")
    public ResponseEntity<String[]> getCoordenates(@PathVariable long id) throws Exception {

        Optional<Article> op = articleService.findById(id);
        if (op.isPresent()) {
            Article article = op.get();
            return new ResponseEntity<>(MapService.getLatitudeLongitude(article.getCITY() + " " + article.getPOSTAL_CODE()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
